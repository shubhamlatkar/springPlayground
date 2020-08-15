package com.shubham.security_jwt.controller;

import com.shubham.security_jwt.document.payload.request.LoginRequest;
import com.shubham.security_jwt.document.payload.request.SignupRequest;
import com.shubham.security_jwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult result) throws Exception {
        return authService.signup(signupRequest, result);
    }

    @GetMapping("/tryAutoLogin")
    public ResponseEntity<?> tryAutoLogin() {
        return ResponseEntity.ok().body("Authenticated");
    }

    @GetMapping("/logmeout")
    public ResponseEntity<?> logout() {
        return authService.logOut();
    }

    @GetMapping("/logoutall")
    public ResponseEntity<?> logoutAll() {
        return authService.logoutAll();
    }

    @PostMapping("/login")
    public ResponseEntity<?> getJwtToken(@RequestBody LoginRequest request) throws Exception {
        return authService.login(request);
    }

}
