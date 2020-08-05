package com.shubham.security_jwt.controller;


import com.shubham.security_jwt.document.Student;
import com.shubham.security_jwt.repository.AuthoritiesRepository;
import com.shubham.security_jwt.repository.RoleRepository;
import com.shubham.security_jwt.repository.StudentRepository;
import com.shubham.security_jwt.repository.UserRepository;
import com.shubham.security_jwt.security.config.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/student")
public class StudentController {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordConfig passwordConfig;
    private final AuthoritiesRepository authoritiesRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(RoleRepository roleRepository, UserRepository userRepository, PasswordConfig passwordConfig, AuthoritiesRepository authoritiesRepository, StudentRepository studentRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordConfig = passwordConfig;
        this.authoritiesRepository = authoritiesRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<?> getStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> getStudent(@PathVariable String id) {
        return ResponseEntity.ok(studentRepository.findById(Long.parseLong(id)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> delStudent(@PathVariable String id) {
        if (studentRepository.existsById(Long.parseLong(id))) {
            studentRepository.deleteById(Long.parseLong(id));
            return ResponseEntity.ok().body("Deleted");
        } else
            return ResponseEntity.badRequest().body("Invalid Id");
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student student) {
        studentRepository.save(student);
        return ResponseEntity.ok().body("Inserted");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> updateStudent(@PathVariable String id, @Valid @RequestBody Student student) {
        return ResponseEntity.ok(studentRepository.findById(Long.parseLong(id)).map(foundUser -> {
            foundUser.setName(student.getName());
            foundUser.setAge(student.getAge());
            foundUser.setPhoneNum(student.getPhoneNum());
            return studentRepository.save(foundUser);
        }).orElseGet(() -> {
            return studentRepository.save(student);
        }));
    }
}
