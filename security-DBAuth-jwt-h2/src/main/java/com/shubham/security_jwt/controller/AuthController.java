package com.shubham.security_jwt.controller;

import com.shubham.security_jwt.document.Authorities;
import com.shubham.security_jwt.document.Role;
import com.shubham.security_jwt.document.Users;
import com.shubham.security_jwt.document.payload.request.LoginRequest;
import com.shubham.security_jwt.document.payload.request.SignupRequest;
import com.shubham.security_jwt.repository.AuthoritiesRepository;
import com.shubham.security_jwt.repository.RoleRepository;
import com.shubham.security_jwt.repository.TokenRepository;
import com.shubham.security_jwt.repository.UserRepository;
import com.shubham.security_jwt.security.config.PasswordConfig;
import com.shubham.security_jwt.security.jwt.JwtTokenUtil;
import com.shubham.security_jwt.security.services.UserDetailsServiceImpl;
import com.shubham.security_jwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final AuthoritiesRepository authoritiesRepository;
    private final RoleRepository roleRepository;
    private final PasswordConfig passwordConfig;
    private final TokenRepository tokenRepository;
    private final AuthService authService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetails, JwtTokenUtil jwtTokenUtil, UserRepository userRepository, AuthoritiesRepository authoritiesRepository, RoleRepository roleRepository, PasswordConfig passwordConfig, TokenRepository tokenRepository, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetails;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
        this.roleRepository = roleRepository;
        this.passwordConfig = passwordConfig;
        this.tokenRepository = tokenRepository;
        this.authService = authService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> insertUsers() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        authoritiesRepository.deleteAll();

        authoritiesRepository.saveAll(
                Arrays.asList(
                        new Authorities("student:read"),
                        new Authorities("student:write")
                )
        );

        Role userRole = new Role("USER");
        userRole.addAuthority(authoritiesRepository.findByAuthority("student:read"));
        roleRepository.save(userRole);

        Role adminRole = new Role("ADMIN");
        adminRole.addAuthority(authoritiesRepository.findByAuthority("student:read"));
        adminRole.addAuthority(authoritiesRepository.findByAuthority("student:write"));
        roleRepository.save(adminRole);

        Users shuUser = new Users(
                "shu",
                "shu@shu.com",
                passwordConfig.passwordEncoder().encode("12as")
        );
        shuUser.addRole(roleRepository.findByRole("USER"));
        shuUser.addRole(roleRepository.findByRole("ADMIN"));
        userRepository.save(shuUser);

        Users knuUser = new Users(
                "knu",
                "knu@shu.com",
                passwordConfig.passwordEncoder().encode("12as")
        );
        knuUser.addRole(roleRepository.findByRole("USER"));
        userRepository.save(knuUser);

        return ResponseEntity.ok().body(userRepository.findAll());
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult result) throws Exception {
        return authService.signup(signupRequest, result);
    }

    @GetMapping("/tryAutoLogin")
    public ResponseEntity<?> tryAutoLogin() {
        return ResponseEntity.ok("ok..");
    }

    @GetMapping("/logmeout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok().body("logged out....");
    }

    @GetMapping("/logoutall")
    public ResponseEntity<?> logoutAll() {
        return ResponseEntity.ok().body("logged out from all sessions....");
    }

    @PostMapping("/login")
    public ResponseEntity<?> getJwtToken(@RequestBody LoginRequest request, HttpServletResponse response) throws Exception {
        return authService.login(request);
    }

}
