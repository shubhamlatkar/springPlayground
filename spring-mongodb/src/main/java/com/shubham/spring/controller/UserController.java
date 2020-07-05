package com.shubham.spring.controller;

import java.util.ArrayList;
import java.util.List;

import com.shubham.spring.model.User;
import com.shubham.spring.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ResponseEntity<ArrayList<User>> getAll() {

        final User temUser = new User("123","shubham", "12as");
        // System.out.println(temUser);
        // this.userRepository.save(new User("shubham", "12as"));

        // System.out.println(userRepository.findAll());
        ArrayList users = new ArrayList<User>();
        users.add(temUser);
        return new ResponseEntity<ArrayList<User>>(users, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}