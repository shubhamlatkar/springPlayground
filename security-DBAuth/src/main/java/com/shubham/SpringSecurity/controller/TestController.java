package com.shubham.SpringSecurity.controller;


import com.shubham.SpringSecurity.document.Role;
import com.shubham.SpringSecurity.document.Student;
import com.shubham.SpringSecurity.repository.AuthoritiesRepository;
import com.shubham.SpringSecurity.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class TestController {

    private final AuthoritiesRepository authoritiesRepository;
    private final RoleRepository roleRepository;

    private static final List<Student> STUDENTS = Arrays.asList(new Student(1, "shubham"), new Student(2, "Kunal"),
            new Student(3, "PS"));

    @Autowired
    public TestController(AuthoritiesRepository authoritiesRepository, RoleRepository roleRepository) {
        this.authoritiesRepository = authoritiesRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/")
    public String defaultGet() {
        List<Role> roles = Arrays.asList(
                new Role("ADMIN", Arrays.asList(
                        authoritiesRepository.findByAuthority("user:write"),
                        authoritiesRepository.findByAuthority("user:read")
                )),
                new Role("USER", Collections.singletonList(
                        authoritiesRepository.findByAuthority("user:write")
                ))
        );

        roleRepository.insert(roles);
        return "STUDENT api in memory authentication";
    }

    @GetMapping("/api/students/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Student getStudent(@PathVariable int id) {
        return STUDENTS.stream().filter(student -> Integer.valueOf(id).equals(new Integer(student.getId()))).findFirst()
                .orElseThrow(() -> new IllegalStateException("student " + id + " DNE"));

    }

    @GetMapping("/management/students")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
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
