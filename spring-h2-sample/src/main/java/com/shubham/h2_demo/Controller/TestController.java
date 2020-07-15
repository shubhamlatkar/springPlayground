package com.shubham.h2_demo.Controller;

import com.shubham.h2_demo.model.Authorities;
import com.shubham.h2_demo.model.Role;
import com.shubham.h2_demo.model.Users;
import com.shubham.h2_demo.repository.AuthoritiesRepository;
import com.shubham.h2_demo.repository.RoleRepository;
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

    public TestController(AuthoritiesRepository authoritiesRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.authoritiesRepository = authoritiesRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @GetMapping("/insert")
    public ResponseEntity<?> insertSampleUsers() {

        List<Authorities> authorities = Arrays.asList(
                new Authorities("user:write"),
                new Authorities("user:read")
        );
        authoritiesRepository.saveAll(authorities);

        List<Role> roles = Arrays.asList(
                new Role("USER"),
                new Role("USER")
        );

        roleRepository.saveAll(roles);

        userRepository.save(new Users(
                "knu",
                "knu@shu.com",
                "$2a$10$hfTAWcZC4tJIq8Vn.FHdb.dNTcKHb27dHNuCG9rlJod3tNe1r4dia"
                ));

        userRepository.save(new Users(
                "shu",
                "shu@shu.com",
                "$2a$10$hfTAWcZC4tJIq8Vn.FHdb.dNTcKHb27dHNuCG9rlJod3tNe1r4dia"
        ));

        return ResponseEntity.ok("Inserted.....");
    }

    @GetMapping("/")
    public ResponseEntity<List<Users>> getDefault() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
