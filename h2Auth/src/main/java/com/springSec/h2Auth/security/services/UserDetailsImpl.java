package com.springSec.h2Auth.security.services;

import com.springSec.h2Auth.model.Authorities;
import com.springSec.h2Auth.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {


    private final String username;
    private final String password;
    private final Set<GrantedAuthority> grantedAuthorities;
    private final String email;
    private final Long id;

    public UserDetailsImpl(String username, String password, Set<GrantedAuthority> grantedAuthorities, String email, Long id) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.email = email;
        this.id = id;
    }

    public static UserDetailsImpl build(User user) {
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .collect(Collectors.toSet());

        user.getRoles().forEach(role -> {
            for (Authorities authorities1 : role.getAuthorities()) {
                authorities.add(new SimpleGrantedAuthority(authorities1.getAuthority()));
            }
        });

        return new UserDetailsImpl(
                user.getUsername(),
                user.getPassword(),
                authorities,
                user.getEmail(),
                user.getId()
        );
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
