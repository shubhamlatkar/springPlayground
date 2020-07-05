package com.shubham.spring.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.shubham.spring.document.Users;
import com.shubham.spring.repository.UserRepository;

@RestController
public class UserController {

    private com.shubham.spring.repository.UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<Users> getAll() {
        userRepository.save(new Users("Shubham", "kiran", 1234L));
        
        return userRepository.findAll();
    }
}
