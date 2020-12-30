package com.springexample.logging.controller;

import com.springexample.logging.model.User;
import com.springexample.logging.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<?> getStudents() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<?> postUser(@RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("Deleted...");
    }
}
