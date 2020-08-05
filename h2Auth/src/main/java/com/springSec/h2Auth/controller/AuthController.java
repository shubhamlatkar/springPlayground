package com.springSec.h2Auth.controller;

import com.springSec.h2Auth.repository.AuthoritiesRepository;
import com.springSec.h2Auth.repository.RoleRepository;
import com.springSec.h2Auth.repository.TokenRepository;
import com.springSec.h2Auth.repository.UserRepository;
import com.springSec.h2Auth.security.config.PasswordConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthoritiesRepository authoritiesRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordConfig passwordConfig;

    public AuthController(AuthoritiesRepository authoritiesRepository, RoleRepository roleRepository, UserRepository userRepository, TokenRepository tokenRepository, PasswordConfig passwordConfig) {
        this.authoritiesRepository = authoritiesRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordConfig = passwordConfig;
    }

    @GetMapping("/insert/role")
    public ResponseEntity<?> defalutGet() {
//        authoritiesRepository.saveAll(Arrays.asList(
//                new Authorities("student:read"),
//                new Authorities("student:write")
//        ));
//
//        Role userRole = new Role("USER");
//        userRole.addAuthoritie(authoritiesRepository.findByAuthority("student:read"));
//        roleRepository.save(userRole);
//
//        Role adminRole = new Role("ADMIN");
//        adminRole.addAuthoritie(authoritiesRepository.findByAuthority("student:read"));
//        adminRole.addAuthoritie(authoritiesRepository.findByAuthority("student:write"));
//        roleRepository.save(adminRole);
//
//        User shuUser = new User("shu", "shu@shu.com", passwordConfig.passwordEncoder().encode("12as"));
//        shuUser.addRole(userRole);
//        shuUser.addRole(adminRole);
//        Token shuToken1 = new Token("shuToken1");
//        Token shuToken2 = new Token("shuToken2");
//        shuUser.addToken(shuToken1);
//        shuUser.addToken(shuToken2);
//        tokenRepository.save(shuToken1);
//        tokenRepository.save(shuToken2);
//        userRepository.save(shuUser);
//
//
//        User knuUser = new User("knu", "knu@shu.com", passwordConfig.passwordEncoder().encode("12as"));
//        knuUser.addRole(userRole);
//        userRepository.save(knuUser);
        return ResponseEntity.ok(userRepository.findAll());
    }

}
