package com.shubham.spring.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Role {
    @Id
    private String Id;
    private String role;
    private List<Authorities> authoritiesList;

    public Role() {
    }

    public Role( String role, List<Authorities> authoritiesList) {
        this.role = role;
        this.authoritiesList = authoritiesList;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Authorities> getAuthoritiesList() {
        return authoritiesList;
    }

    public void setAuthoritiesList(List<Authorities> authoritiesList) {
        this.authoritiesList = authoritiesList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "Id='" + Id + '\'' +
                ", role='" + role + '\'' +
                ", authoritiesList=" + authoritiesList +
                '}';
    }
}
