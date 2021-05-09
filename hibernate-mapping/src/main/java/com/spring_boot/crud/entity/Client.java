package com.spring_boot.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    private String location;

    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Employee> employees;

    public Client() {
    }

    public Client(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Client(long id, String name, String location, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.employees = employees;
    }

    public Client(long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployee(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", employees=" + employees +
                '}';
    }

    public void addEmployee(Employee employee) {
        if (employee == null)
            employees = new ArrayList<Employee>();
        employees.add(employee);
    }

    @PreRemove
    public void beforeRemove() {
        if (!this.employees.isEmpty())
            throw new RuntimeException("Can't be removed as it has children's associated with it");
    }
}
