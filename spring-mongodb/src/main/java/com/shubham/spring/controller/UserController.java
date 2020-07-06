package com.shubham.spring.controller;


import java.util.List;

import com.shubham.spring.document.Users;
import com.shubham.spring.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private com.shubham.spring.repository.UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Users>> getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
