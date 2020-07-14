package com.shubham.spring.controller;


import com.shubham.spring.document.Users;
import com.shubham.spring.repository.AuthoritiesRepository;
import com.shubham.spring.repository.RoleRepository;
import com.shubham.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AuthoritiesRepository authoritiesRepository;

    @Autowired
    public UserController(UserRepository userRepository, RoleRepository roleRepository, AuthoritiesRepository authoritiesRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authoritiesRepository = authoritiesRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Users>> getAll() {
//        List<Authorities> authorities = Arrays.asList(
//                new Authorities("user:write"),
//                new Authorities("user:read")
//        );
//
//        authoritiesRepository.insert(authorities);

//        List<Role> roles = Arrays.asList(
//                new Role("ADMIN", Arrays.asList(
//                        authoritiesRepository.findByAuthority("user:write"),
//                        authoritiesRepository.findByAuthority("user:read")
//                )),
//                new Role("USER", Collections.singletonList(
//                        authoritiesRepository.findByAuthority("user:write")
//                ))
//        );
//
//        roleRepository.insert(roles);

    //        userRepository.insert(new Users(
    //                "kunal",
    //                "knu@shu.com",
    //                "kunal",
    //                Collections.singletonList(
    //                        roleRepository.findByRole("USER")
    //                )
    //        ));

        userRepository.findByUsername("kunal")
                .getRoles()
                .forEach(role -> {
                    role.getAuthoritiesList()
                            .forEach(authorities -> System.out.println(authorities.getAuthority()));
                });

        return ResponseEntity.ok(userRepository.findAll());
    }
}
