package com.shubham.SpringSecurity.security.services;

import com.shubham.SpringSecurity.document.Authorities;
import com.shubham.SpringSecurity.document.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class UserDetailsImpl implements UserDetails {


    private final List<? extends GrantedAuthority> grantedAuthorities;
    private final String password;
    private final String username;
    private final Boolean isAccountNonExpired;
    private final Boolean isAccountNonLocked;
    private final Boolean isCredentialsNonExpired;
    private final Boolean isEnabled;
    private final String email;
    private final String id;

    public UserDetailsImpl(
            List<? extends GrantedAuthority> grantedAuthorities,
            String password,
            String username,
            Boolean isAccountNonExpired,
            Boolean isAccountNonLocked,
            Boolean isCredentialsNonExpired,
            Boolean isEnabled,
            String email, String id) {
        this.grantedAuthorities = grantedAuthorities;
        this.password = password;
        this.username = username;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.email = email;
        this.id = id;
    }

    public static UserDetailsImpl build(Users user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .collect(Collectors.toList());

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
                authorities,
                user.getPassword(),
                user.getUsername(),
                true,
                true,
                true,
                true,
                user.getEmail(),
                user.getId()
        );
    }

    public List<? extends GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public Boolean getAccountNonExpired() {
        return isAccountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return isAccountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}