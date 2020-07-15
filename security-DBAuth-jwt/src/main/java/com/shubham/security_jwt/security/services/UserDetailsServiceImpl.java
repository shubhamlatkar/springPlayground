package com.shubham.security_jwt.security.services;


import com.shubham.security_jwt.document.Authorities;
import com.shubham.security_jwt.document.Users;
import com.shubham.security_jwt.repository.UserRepository;
import com.shubham.security_jwt.security.config.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    private final PasswordConfig passwordConfig;

    @Autowired
    public UserDetailsServiceImpl(PasswordConfig passwordConfig) {
        this.passwordConfig = passwordConfig;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .collect(Collectors.toSet());


        user.getRoles().forEach(role -> {
            for (Authorities authorities1 : role.getAuthoritiesList()) {
                authorities.add(new SimpleGrantedAuthority(authorities1.getAuthority()));
            }
        });

        return UserDetailsImpl.build(user);
    }

}
