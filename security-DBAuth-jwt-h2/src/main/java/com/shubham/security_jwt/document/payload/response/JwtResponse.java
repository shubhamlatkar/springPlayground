package com.shubham.security_jwt.document.payload.response;

public class JwtResponse {
    private String token;
    private Long id;
    private String username;

    public JwtResponse(String accessToken, Long id, String username) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
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
}
