package com.spring_boot.crud.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aadhaar_id", referencedColumnName = "id")
    private Aadhaar aadhaar;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @Basic(optional = true)
    private List<Token> tokens = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @Basic(optional = true)
    @JoinColumn(name = "client_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client client = new Client();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")
            }
    )
    private List<Role> roles = new ArrayList<>();

    public Employee() {
    }

    public Employee(String name, Aadhaar aadhaar) {
        this.name = name;
        this.aadhaar = aadhaar;
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(long id, String name, Aadhaar aadhaar) {
        this.id = id;
        this.name = name;
        this.aadhaar = aadhaar;
    }

    public Employee(long id, String name, Aadhaar aadhaar, List<Token> tokens, Client client, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.aadhaar = aadhaar;
        this.tokens = tokens;
        this.client = client;
        this.roles = roles;
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

    public Aadhaar getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(Aadhaar aadhaar) {
        this.aadhaar = aadhaar;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        client.addEmployee(this);
        this.client = client;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addAadhaar(Aadhaar aadhaar) {
        aadhaar.setEmployee(this);
        setAadhaar(aadhaar);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", aadhaar=" + aadhaar +
                ", tokens=" + tokens +
                ", client=" + client +
                ", roles=" + roles +
                '}';
    }

    public void addToken(Token token) {
        if (tokens == null)
            tokens = new ArrayList<>();
        tokens.add(token);
        token.setEmployee(this);
    }

    public void addRole(Role role) {
        if (roles == null)
            roles = new ArrayList<>();
        roles.add(role);
    }

    public void removeRole(Role role) {
        if (roles == null)
            return;
        roles.remove(role);
    }

}



