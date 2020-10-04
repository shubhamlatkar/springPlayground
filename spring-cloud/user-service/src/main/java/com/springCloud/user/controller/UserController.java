package com.springCloud.user.controller;

import com.springCloud.user.model.User;
import com.springCloud.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Value("${greeting}")
    private String greeting;

    @Autowired
    public UserController(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getDefault() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PutMapping("/")
    public ResponseEntity<User> updateMe(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userRepository.findByUsername(user.getUsername()).map(foundUser -> {
            foundUser.setUsername(user.getUsername());
            foundUser.setEmail(user.getEmail());
            return userRepository.save(foundUser);
        }).orElseGet(() -> {
            return userRepository.save(user);
        }));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteMe(@RequestBody String username) {
        try {
            userRepository.delete(Objects.requireNonNull(userRepository.findByUsername(username).orElse(null)));
            return ResponseEntity.ok().body("Deleted");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Non existing");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> postUser(@Valid @RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername()))
            return ResponseEntity.badRequest().body("Exited");
        userRepository.save(user);
        return ResponseEntity.ok().body("Inserted");
    }

    @GetMapping("/greeting")
    public ResponseEntity<String> getTestData() {
        return ResponseEntity.ok(greeting);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userRepository.findByUsername(username));
    }

    @GetMapping("/getName/{id}")
    public ResponseEntity<?> getByUsername(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        return ResponseEntity.ok(user.getUsername());
    }

}
