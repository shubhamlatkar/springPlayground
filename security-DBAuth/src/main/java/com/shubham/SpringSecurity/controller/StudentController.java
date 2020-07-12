package com.shubham.SpringSecurity.controller;

import com.shubham.SpringSecurity.model.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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
        return STUDENTS.stream().filter(student -> Integer.valueOf(id).equals(new Integer(student.getId()))).findFirst()
                .orElseThrow(() -> new IllegalStateException("student " + id + " DNE"));

    }

    @GetMapping("/management/students")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Student> getStudents() {
        return STUDENTS;
    }

    @DeleteMapping("/management/students/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void delStudent(@PathVariable int id) {
        System.out.println("In del" + id);
    }

    @PostMapping("/management/students")
    @PreAuthorize("hasAuthority('student:write')")
    public void addStudent(@RequestBody Student student) {
        System.out.println("post insert" + student);
    }

    @PutMapping("/management/students/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable int id, @RequestBody Student student) {
        System.out.println("In update" + id + student);
    }
}