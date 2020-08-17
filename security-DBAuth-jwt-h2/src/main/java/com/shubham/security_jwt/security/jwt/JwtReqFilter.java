package com.shubham.security_jwt.security.jwt;

import com.shubham.security_jwt.document.Users;
import com.shubham.security_jwt.repository.TokenRepository;
import com.shubham.security_jwt.repository.UserRepository;
import com.shubham.security_jwt.security.services.UserDetailsImpl;
import com.shubham.security_jwt.security.services.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Component
public class JwtReqFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final TokenRepository tokenRepository;

    private final UserRepository userRepository;

    @Autowired
    public JwtReqFilter(JwtTokenUtil jwtTokenUtil, UserDetailsServiceImpl userDetailsService, TokenRepository tokenRepository, UserRepository userRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = httpServletRequest.getHeader("Authorization");

        Cookie[] cookies = httpServletRequest.getCookies();
        String cookieJWT = null;
        String username = null;
        String jwt = null;
        if (cookies != null) {
            jwt = Arrays.stream(cookies)
                    .filter(c -> c.getName().equals("jwt"))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
        }

        if (authorization != null && authorization.startsWith("Bearer "))
            jwt = authorization.substring(7);
        try {
            if (jwt != null)
                username = jwtTokenUtil.getUsernameFromToken(jwt);
        } catch (ExpiredJwtException | MalformedJwtException e) {
            e.printStackTrace();
        }

        Users user = userRepository.findByUsername(username).orElse(null);

        String finalJwt = jwt;
        if (user != null) {
            if (!tokenRepository.existsByToken(finalJwt) && username != null)
                httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            if (httpServletRequest.getRequestURL().toString().contains("/logmeout")) {
                tokenRepository.delete(Objects.requireNonNull(tokenRepository.findByToken(finalJwt).orElse(null)));
            }
            if (httpServletRequest.getRequestURL().toString().contains("/logoutall")) {
                user.setActiveTokens(new ArrayList<>());
                userRepository.save(user);
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            usernamePasswordAuthenticationToken
                    .setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
                    );
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
