package com.spring_demo.reactive_demo.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document
public class Employee {
    @Id
    private String id;
    private String name;
    private String dept;

    public Employee(String name, String dept) {
        this.name = name;
        this.dept = dept;
    }
}
