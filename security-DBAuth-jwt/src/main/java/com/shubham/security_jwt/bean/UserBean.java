package com.shubham.security_jwt.bean;

import com.shubham.security_jwt.document.Users;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserBean {

    private Users user;
    private String token;
    public UserBean() {
    }

    public void setUser(Users user, String token) {
        this.user = user;
        this.token = token;
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

    public void removeToken() {
        List<String> tokens = null;
        if (user.getActiveTokens() != null && user.getActiveTokens().contains(token)) {
            tokens = user.getActiveTokens();
            tokens.remove(token);
            user.setActiveTokens(tokens);
        }
    }

    public void removeAllTokens() {
        user.setActiveTokens(new ArrayList<String>());
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "user=" + user +
                ", token='" + token + '\'' +
                '}';
    }
}
