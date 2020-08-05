package com.shubham.security_jwt.document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String role;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "roles_auths",
            joinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "Id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "auth_id", referencedColumnName = "id")
            }
    )
    private List<Authorities> authorities;

    @JsonIgnore
    @ManyToMany(targetEntity = Users.class, cascade = CascadeType.PERSIST, mappedBy = "roles")
    private List<Users> users = new ArrayList<>();

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(String role, List<Authorities> authorities) {
        this.role = role;
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

    public List<Authorities> getAuthoritiesList() {
        return authorities;
    }

    public void setAuthoritiesList(List<Authorities> authoritiesList) {
        this.authorities = authoritiesList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "Id='" + Id + '\'' +
                ", role='" + role + '\'' +
                ", authoritiesList=" + authorities +
                '}';
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Users> users) {
        this.users = users;
    }

    public void addAuthority(Authorities authority) {
        if (authorities == null)
            authorities = new ArrayList<>();
        authorities.add(authority);
    }
}
