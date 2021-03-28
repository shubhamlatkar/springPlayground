package com.spring_mvn.dependency_injection.life_cycle_methods.lifecycle_using_annotation;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Employee {
    private int id;
    private String name;

    public Employee() {
    }

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("In destroy method given by annotattion");
    }

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        System.out.println("In init method given by annotation ");
    }
}
