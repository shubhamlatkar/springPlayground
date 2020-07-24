package com.shubham.h2_demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String role;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "roles_auths", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "auth_id")})
    private List<Authorities> authorities;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "tokens")
    private Set<Users> users = new HashSet<>();

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(String role, List<Authorities> authorities) {
        this.role = role;
        this.authorities = authorities;
    }

    public List<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authorities> authorities) {
        this.authorities = authorities;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "Id=" + Id +
                ", role='" + role + '\'' +
                ", authorities=" + authorities +
                '}';
    }

    public void addAuthoritie(Authorities authoritie) {
        if (authorities == null)
            authorities = new ArrayList<>();
        authorities.add(authoritie);
    }
}
