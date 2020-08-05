package com.shubham.security_jwt.repository;

import com.shubham.security_jwt.document.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
