package com.springexample.logging.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @GetMapping("/")
    public ResponseEntity<?> getStudents() {
        return ResponseEntity.ok("Students");
    }
}
