package com.spring_boot.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String role;

    @JsonIgnore
    @ManyToMany(targetEntity = Employee.class, cascade = CascadeType.PERSIST, mappedBy = "roles")
    private List<Employee> employees = new ArrayList<>();

    public Role() {
    }

    public Role(Long id, String role, List<Employee> employees) {
        this.id = id;
        this.role = role;
        this.employees = employees;
    }

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Role(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", employees=" + employees +
                '}';
    }

    @PreRemove
    public void beforeRemove() {
        if (!this.employees.isEmpty())
            throw new RuntimeException("Can't be removed as it has children's associated with it");
    }
}