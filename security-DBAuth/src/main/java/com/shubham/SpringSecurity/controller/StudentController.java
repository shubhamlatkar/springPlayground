package com.shubham.SpringSecurity.controller;

import java.util.Arrays;
import java.util.List;

import com.shubham.SpringSecurity.model.Student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(new Student(1, "shubham"), new Student(2, "Kunal"),
            new Student(3, "PS"));

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable int id) {
        return STUDENTS.stream()
        .filter(student -> new Integer(id).equals(new Integer(student.getId())))
        .findFirst()
        .orElseThrow(() -> new IllegalStateException("student " + id + " DNE"));

    }
}