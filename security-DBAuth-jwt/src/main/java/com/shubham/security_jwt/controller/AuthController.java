package com.shubham.security_jwt.controller;

import com.shubham.security_jwt.document.Users;
import com.shubham.security_jwt.document.payload.request.LoginRequest;
import com.shubham.security_jwt.document.payload.request.SignupRequest;
import com.shubham.security_jwt.document.payload.response.JwtResponse;
import com.shubham.security_jwt.repository.UserRepository;
import com.shubham.security_jwt.security.jwt.JwtTokenUtil;
import com.shubham.security_jwt.security.services.UserDetailsImpl;
import com.shubham.security_jwt.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepositor;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetails, JwtTokenUtil jwtTokenUtil, UserRepository userRepositor) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetails;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepositor = userRepositor;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult result) throws Exception {
        return (userDetailsService.saveUser(signupRequest) && !result.hasErrors()) ? ResponseEntity.ok().body("Saved") : ResponseEntity.badRequest().body("Bad Cerd");
    }

    @GetMapping("/tryAutoLogin")
    public ResponseEntity<?> tryAutoLogin() {
        return ResponseEntity.ok().body("Authenticated");
    }

    @GetMapping("/logmeout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok().body("logged out....");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> getJwtToken(@RequestBody LoginRequest request, HttpServletResponse response) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw e;
        }

        final UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(
                request.getUsername()
        );

        final String jwtToken = jwtTokenUtil.generateToken(userDetails);
        Users user = userRepositor.findByUsername(request.getUsername()).orElse(null);

        List<String> tokens = user.getActiveTokens() != null ? user.getActiveTokens() : new ArrayList<String>();
        tokens.add(jwtToken);
        user.setActiveTokens(tokens);

        userRepositor.save(user);

        final Cookie cookie = new Cookie("jwt", jwtToken);
        cookie.setMaxAge(24 * 60 * 60); // expires in 1 days
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(new JwtResponse(jwtToken, userDetails.getId(), userDetails.getUsername()));
    }

}
