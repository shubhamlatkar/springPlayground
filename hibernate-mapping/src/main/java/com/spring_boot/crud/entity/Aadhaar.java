package com.spring_boot.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Aadhaar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long number;
    private String dob;

    @OneToOne(mappedBy = "aadhaar")
    @JsonIgnore
    private Employee employee;

    public Aadhaar() {
    }

    public Aadhaar(long number, String dob) {
        this.number = number;
        this.dob = dob;
    }

    public Aadhaar(String dob) {
        this.dob = dob;
    }

    public Aadhaar(String dob, Employee employee) {
        this.dob = dob;
        this.employee = employee;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Aadhaar{" +
                "id=" + id +
                ", number=" + number +
                ", dob='" + dob + '\'' +
                ", employee=" + employee +
                '}';
    }
}
