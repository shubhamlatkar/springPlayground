package com.shubham.springSecurity.controller;

import java.util.Arrays;
import java.util.List;

import com.shubham.springSecurity.model.Student;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(new Student(1, "shubham"), new Student(2, "Kunal"),
            new Student(3, "PS"));

    @GetMapping("/")
    public String defaultGet() {
        return "STUDENT api in memory authentication";
    }
    
    @GetMapping("/api/students/{id}")
    public Student getStudent(@PathVariable int id) {
        return STUDENTS.stream()
        .filter(student -> new Integer(id).equals(new Integer(student.getId())))
        .findFirst()
        .orElseThrow(() -> new IllegalStateException("student " + id + " DNE"));

    }

    @GetMapping("/management/students")
    public List<Student> getStudents() {
        return STUDENTS;
    }

    @DeleteMapping("/management/students/{id}")
    public void delStudent(@PathVariable int id) {
        System.out.println("In del" + id);
    }
    
    @PostMapping("/management/students")
    public void addStudent(@RequestBody Student student) {
        System.out.println("post insert" + student);
    }

    @PutMapping("/management/students/{id}")
    public void updateStudent(@PathVariable int id,@RequestBody Student student ) {
        System.out.println("In update" + id + student);
    }
}