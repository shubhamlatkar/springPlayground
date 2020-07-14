package com.shubham.SpringSecurity.security.services;

import com.shubham.SpringSecurity.document.Authorities;
import com.shubham.SpringSecurity.document.Users;
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
    private final String id;

    public UserDetailsImpl(String username, String password, Set<GrantedAuthority> grantedAuthorities, String email, String id) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.email = email;
        this.id = id;
    }

    public static UserDetailsImpl build(Users user) {
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .collect(Collectors.toSet());

//        authorities.add(user.getRoles()
//                .stream()
//                .map(role -> {
//                    return role.getAuthoritiesList()
//                           .stream()
//                           .map(authorities1 -> new SimpleGrantedAuthority(authorities1.getAuthority()))
//                           .collect(Collectors.toList());
//                })
//                .collect(Collectors.toList())
//        );

        user.getRoles().forEach(role -> {
            for (Authorities authorities1 : role.getAuthoritiesList()) {
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

    public String getId() {
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