package com.shubham.h2_demo.model;

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
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="user_id")
    private Users user;

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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
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
}
