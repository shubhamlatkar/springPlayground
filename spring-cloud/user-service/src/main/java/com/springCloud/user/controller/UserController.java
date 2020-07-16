package com.springCloud.user.controller;

import com.springCloud.user.model.User;
import com.springCloud.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/insert")
    public String insertData() {
        userRepository.saveAll(Arrays.asList(new User("shubham","shu@shu.com"), new User("knu", "knu@shu.com")));
        return "Inserted ...";
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getDefault() {
        return ResponseEntity.ok(userRepository.findAll());
    }

}
