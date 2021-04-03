package com.spring_mvn.dao;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDao {

    public void init() {
        System.out.println("Init method");
    }

    public void destroy() {
        System.out.println("Destroy method ");
    }

    public void create() {
        System.out.println("Created the emp object");
    }
}
