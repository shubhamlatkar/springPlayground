package com.shubham.security_jwt.service;

import com.shubham.security_jwt.document.Tokens;
import com.shubham.security_jwt.document.Users;
import com.shubham.security_jwt.document.payload.request.LoginRequest;
import com.shubham.security_jwt.document.payload.request.SignupRequest;
import com.shubham.security_jwt.document.payload.response.JwtResponse;
import com.shubham.security_jwt.repository.TokenRepository;
import com.shubham.security_jwt.repository.UserRepository;
import com.shubham.security_jwt.security.jwt.JwtTokenUtil;
import com.shubham.security_jwt.security.services.UserDetailsImpl;
import com.shubham.security_jwt.security.services.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.servlet.http.Cookie;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final TokenRepository tokenRepository;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtTokenUtil jwtTokenUtil, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.tokenRepository = tokenRepository;
    }

    public ResponseEntity<?> signup(SignupRequest signupRequest, BindingResult result) {
        return (userDetailsService.saveUser(signupRequest) && !result.hasErrors()) ? ResponseEntity.ok().body("Saved") : ResponseEntity.badRequest().body("Bad Credentials");
    }

    public ResponseEntity<?> login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Bad cred");
        }

        final UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(
                request.getUsername()
        );

        final String jwtToken = jwtTokenUtil.generateToken(userDetails);
        Users user = userRepository.findByUsername(request.getUsername()).orElse(null);

        assert user != null;
        Tokens tokens1 = new Tokens(jwtToken);
        user.addToken(tokens1);
        tokenRepository.save(tokens1);
        userRepository.save(user);

        final Cookie cookie = new Cookie("jwt", jwtToken);
        cookie.setMaxAge(24 * 60 * 60); // expires in 1 days
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
//        response.addCookie(cookie);
        return ResponseEntity.ok(new JwtResponse(jwtToken, userDetails.getId(), userDetails.getUsername()));

    }
}