package com.shubham.spring.controller;

import java.util.List;

import com.shubham.spring.model.User;
import com.shubham.spring.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAll() {

        final User temUser = new User("shubham", "12as");
        System.out.println(temUser);
        this.userRepository.save(new User("shubham", "12as"));

        System.out.println(userRepository.findAll());

        return new ResponseEntity<List<User>>(this.userRepository.findAll(),HttpStatus.OK);
    }

}