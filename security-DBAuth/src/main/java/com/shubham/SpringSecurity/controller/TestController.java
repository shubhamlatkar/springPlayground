package com.shubham.SpringSecurity.controller;


import com.shubham.SpringSecurity.document.Student;
import com.shubham.SpringSecurity.repository.RoleRepository;
import com.shubham.SpringSecurity.repository.UserRepository;
import com.shubham.SpringSecurity.security.config.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class TestController {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordConfig passwordConfig;

    private static final List<Student> STUDENTS = Arrays.asList(new Student(1, "shubham"), new Student(2, "Kunal"),
            new Student(3, "PS"));

    @Autowired
    public TestController(RoleRepository roleRepository, UserRepository userRepository, PasswordConfig passwordConfig) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordConfig = passwordConfig;
    }

    @GetMapping("/")
    public String defaultGet() {
//        userRepository.insert(new Users(
//                "knu",
//                "knu@shu.com",
//                passwordConfig.passwordEncoder().encode("12as"),
//                Collections.singletonList(
//                        roleRepository.findByRole("USER")
//                )
//        ));
//
//        userRepository.insert(new Users(
//                "shu",
//                "shu@shu.com",
//                passwordConfig.passwordEncoder().encode("12as"),
//                Collections.singletonList(
//                        roleRepository.findByRole("USER")
//                )
//        ));
        return "STUDENT api in memory authentication DB Auth";
    }

    @GetMapping("/test/data")
    public String testGet() {
        return "Secured user";
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
    @PreAuthorize("hasAuthority('user:write')")
    public void delStudent(@PathVariable int id) {
        System.out.println("In del" + id);
    }

    @PostMapping("/management/students")
    @PreAuthorize("hasAuthority('user:write')")
    public void addStudent(@RequestBody Student student) {
        System.out.println("post insert" + student);
    }

    @PutMapping("/management/students/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public void updateStudent(@PathVariable int id, @RequestBody Student student) {
        System.out.println("In update" + id + student);
    }
}
