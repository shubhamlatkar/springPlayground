package com.spring_boot.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String token;

    @JsonIgnore
    @ManyToOne(targetEntity = Employee.class, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "emp_id")
    private Employee employee;



    public Token() {
    }

    public Token(String token) {
        this.token = token;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Token{" +
                "Id=" + Id +
                ", token='" + token + '\'' +
                '}';
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}