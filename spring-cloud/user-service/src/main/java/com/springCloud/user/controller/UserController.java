package com.springCloud.user.controller;

import com.springCloud.user.model.User;
import com.springCloud.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public UserController(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/insert")
    public String insertData() {
        userRepository.saveAll(Arrays.asList(new User("shubham", "shu@shu.com"), new User("knu", "knu@shu.com")));
        return "Inserted ...";
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getDefault() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PutMapping("/me")
    public ResponseEntity<User> updateMe(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userRepository.findByUsername(user.getUsername()).map(foundUser -> {
            foundUser.setUsername(user.getUsername());
            foundUser.setEmail(user.getEmail());
            return userRepository.save(foundUser);
        }).orElseGet(() -> {
            return userRepository.save(user);
        }));
    }

    @DeleteMapping("/me")
    public ResponseEntity<?> deleteMe(@RequestBody String username) {
        try {
            userRepository.delete(userRepository.findByUsername(username).orElse(null));
            return ResponseEntity.ok().body("Deleted");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Non existing");
        }
    }

    @PostMapping("/user")
    public ResponseEntity<?> postUser(@Valid @RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername()))
            return ResponseEntity.badRequest().body("Exited");
        userRepository.save(user);
        return ResponseEntity.ok().body("Inserted");
    }

    @GetMapping("/test")
    public ResponseEntity<String> getTestData() {
        return ResponseEntity.ok("Hello World");
    }
}
