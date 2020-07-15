package com.shubham.security_jwt.document;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Authorities {
    @Id
    private String id;
    private String authority;

    public Authorities() {
    }

    public Authorities( String authority) {
        this.authority = authority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "id='" + id + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
