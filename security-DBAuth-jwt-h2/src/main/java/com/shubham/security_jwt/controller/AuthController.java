package com.shubham.security_jwt.controller;

import com.shubham.security_jwt.document.Role;
import com.shubham.security_jwt.document.Tokens;
import com.shubham.security_jwt.document.Users;
import com.shubham.security_jwt.document.payload.request.LoginRequest;
import com.shubham.security_jwt.document.payload.request.SignupRequest;
import com.shubham.security_jwt.document.payload.response.JwtResponse;
import com.shubham.security_jwt.repository.AuthoritiesRepository;
import com.shubham.security_jwt.repository.RoleRepository;
import com.shubham.security_jwt.repository.TokenRepository;
import com.shubham.security_jwt.repository.UserRepository;
import com.shubham.security_jwt.security.config.PasswordConfig;
import com.shubham.security_jwt.security.jwt.JwtTokenUtil;
import com.shubham.security_jwt.security.services.UserDetailsImpl;
import com.shubham.security_jwt.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.stream.Collectors;

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

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetails, JwtTokenUtil jwtTokenUtil, UserRepository userRepository, AuthoritiesRepository authoritiesRepository, RoleRepository roleRepository, PasswordConfig passwordConfig, TokenRepository tokenRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetails;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
        this.roleRepository = roleRepository;
        this.passwordConfig = passwordConfig;
        this.tokenRepository = tokenRepository;
    }

    @GetMapping("/insert/roles")
    public ResponseEntity<?> insertUsers() {
//        userRepository.deleteAll();
//        roleRepository.deleteAll();
//        authoritiesRepository.deleteAll();
//
//        authoritiesRepository.saveAll(
//                Arrays.asList(
//                        new Authorities("student:read"),
//                        new Authorities("student:write")
//                )
//        );
//
//        Role userRole = new Role("USER");
//        userRole.addAuthority(authoritiesRepository.findByAuthority("student:read"));
//        roleRepository.save(userRole);
//
//        Role adminRole = new Role("ADMIN");
//        adminRole.addAuthority(authoritiesRepository.findByAuthority("student:read"));
//        adminRole.addAuthority(authoritiesRepository.findByAuthority("student:write"));
//        roleRepository.save(adminRole);
//
//        Users shuUser = new Users(
//                "shu",
//                "shu@shu.com",
//                passwordConfig.passwordEncoder().encode("12as")
//        );
//        shuUser.addRole(roleRepository.findByRole("USER"));
//        shuUser.addRole(roleRepository.findByRole("ADMIN"));
//        userRepository.save(shuUser);
//
//        Users knuUser = new Users(
//                "knu",
//                "knu@shu.com",
//                passwordConfig.passwordEncoder().encode("12as")
//        );
//        knuUser.addRole(roleRepository.findByRole("USER"));
//        userRepository.save(knuUser);

        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult result) throws Exception {
        return (userDetailsService.saveUser(signupRequest) && !result.hasErrors()) ? ResponseEntity.ok().body("Saved") : ResponseEntity.badRequest().body("Bad Cerd");
    }

    @GetMapping("/tryAutoLogin")
    public ResponseEntity<?> tryAutoLogin() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userRepository.findByUsername(username).orElse(null);
        return ResponseEntity.ok(new JwtResponse("", user.getId() != null ? user.getId() : 0l, user.getUsername(), user.getRoles().stream().map(Role::getRole).collect(Collectors.toList())));
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
        Users user = userRepository.findByUsername(request.getUsername()).orElse(null);
        Tokens token = new Tokens(jwtToken);
        assert user != null;
        user.addToken(token);
        tokenRepository.save(token);
        userRepository.save(user);

        final Cookie cookie = new Cookie("jwt", jwtToken);
        cookie.setMaxAge(24 * 60 * 60); // expires in 1 days
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(new JwtResponse(jwtToken, userDetails.getId(), userDetails.getUsername(), user.getRoles().stream().map(Role::getRole).collect(Collectors.toList())));
    }

}
