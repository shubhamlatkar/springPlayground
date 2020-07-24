package com.shubham.h2_demo.Controller;

import com.shubham.h2_demo.model.Authorities;
import com.shubham.h2_demo.model.Role;
import com.shubham.h2_demo.model.Token;
import com.shubham.h2_demo.model.Users;
import com.shubham.h2_demo.repository.AuthoritiesRepository;
import com.shubham.h2_demo.repository.RoleRepository;
import com.shubham.h2_demo.repository.TokenRepository;
import com.shubham.h2_demo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TestController {

    private final AuthoritiesRepository authoritiesRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;

    public TestController(AuthoritiesRepository authoritiesRepository, UserRepository userRepository, RoleRepository roleRepository, TokenRepository tokenRepository) {
        this.authoritiesRepository = authoritiesRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
    }

    @GetMapping("/insert/token")
    public ResponseEntity<?> insertToken() {
        Users user = userRepository.findByUsername("shu").orElse(null);
        System.out.println(user);
        if (user != null) {
            Token shuToken = new Token("shuToken1");
            System.out.println(shuToken);
            user.addToken(shuToken);
            userRepository.save(user);
        }
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/insert")
    public ResponseEntity<?> insertSampleUsers() {

        List<Authorities> authorities = Arrays.asList(
                new Authorities("user:write"),
                new Authorities("user:read")
        );
        authoritiesRepository.saveAll(authorities);

        Role userRole = new Role("USER");
        userRole.addAuthoritie(authoritiesRepository.findByAuthority("user:read"));
        roleRepository.save(userRole);

        Role adminRole = new Role("ADMIN");
        adminRole.addAuthoritie(authoritiesRepository.findByAuthority("user:read"));
        adminRole.addAuthoritie(authoritiesRepository.findByAuthority("user:write"));
        roleRepository.save(adminRole);


//        List<Role> roles = Arrays.asList(
//                new Role("USER", Collections.singletonList(authoritiesRepository.findByAuthority("user:read"))),
//                new Role("ADMIN", Arrays.asList(authoritiesRepository.findByAuthority("user:read"), authoritiesRepository.findByAuthority("user:write")))
//        );
//        roleRepository.saveAll(roles);


        Users knu = new Users(
                "knu",
                "knu@shu.com",
                "12as"
        );
        knu.addRole(roleRepository.findByRole("USER"));
        userRepository.save(knu);

        Users shu = new Users(
                "shu",
                "shu@shu.com",
                "12as"
        );
        shu.addRole(roleRepository.findByRole("USER"));
        shu.addRole(roleRepository.findByRole("ADMIN"));
        userRepository.save(shu);

        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/")
    public ResponseEntity<List<Users>> getDefault() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
