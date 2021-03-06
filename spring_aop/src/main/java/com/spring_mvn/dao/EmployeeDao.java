package com.spring_mvn.dao;

import com.spring_mvn.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    int create(Employee employee);

    int update(Employee employee);

    int delete(Employee employee);

    Employee read(int id);

    List<Employee> realAll();
}
