package com.shubham.security_jwt.document.payload.response;

import java.util.List;

public class JwtResponse {
    private String token;
    private List<String> type;
    private Long id;
    private String username;

    public JwtResponse(String accessToken, Long id, String username, List<String> type) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.type = type;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }
}
