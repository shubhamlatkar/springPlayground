package com.shubham.security_jwt.controller;


import com.shubham.security_jwt.document.Student;
import com.shubham.security_jwt.repository.AuthoritiesRepository;
import com.shubham.security_jwt.repository.RoleRepository;
import com.shubham.security_jwt.repository.UserRepository;
import com.shubham.security_jwt.security.config.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TestController {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordConfig passwordConfig;
    private final AuthoritiesRepository authoritiesRepository;

    private static final List<Student> STUDENTS = Arrays.asList(new Student(1, "shubham"), new Student(2, "Kunal"),
            new Student(3, "PS"));

    @Autowired
    public TestController(RoleRepository roleRepository, UserRepository userRepository, PasswordConfig passwordConfig, AuthoritiesRepository authoritiesRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordConfig = passwordConfig;
        this.authoritiesRepository = authoritiesRepository;
    }

    @GetMapping("/")
    public String defaultGet() {
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
