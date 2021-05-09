package com.spring_boot.crud.repository;

import com.spring_boot.crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT * from employee WHERE id BETWEEN 10 AND 50 AND name LIKE '%8%'", nativeQuery = true)
    public List<Employee> findByNameAndId();
}

