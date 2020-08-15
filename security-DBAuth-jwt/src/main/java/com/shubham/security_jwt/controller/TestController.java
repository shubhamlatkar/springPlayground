package com.shubham.security_jwt.controller;


import com.shubham.security_jwt.document.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user/")
public class TestController {

    private static List<Student> STUDENTS = Arrays.asList(new Student(1, "shubham"), new Student(2, "Kunal"),
            new Student(3, "PS"));

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getStudent(@PathVariable int id) {
        return ResponseEntity.ok(STUDENTS.stream().filter(student -> Integer.valueOf(id).equals(student.getId())).findFirst()
                .orElseThrow(() -> new IllegalStateException("student " + id + " DNE")));
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')")
    public List<Student> getStudents() {
        return STUDENTS;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> delStudent(@PathVariable int id) {
        STUDENTS = STUDENTS.stream().filter(student -> !(Integer.valueOf(id).equals(student.getId()))).collect(Collectors.toList());
        return ResponseEntity.ok(STUDENTS);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<List<Student>> addStudent(@RequestBody Student student) {
        STUDENTS.add(student);
        return ResponseEntity.ok(STUDENTS);
    }

    @PutMapping("/")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> updateStudent( @RequestBody Student student) {
        STUDENTS = STUDENTS.stream().filter(student1 ->
                !(Integer.valueOf(student.getId()).equals(student1.getId()))
        ).collect(Collectors.toList());
        STUDENTS.add(student);
        return ResponseEntity.ok(STUDENTS);
    }
}
