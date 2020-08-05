package com.springSec.h2Auth.security.services;


import com.springSec.h2Auth.model.User;
import com.springSec.h2Auth.repository.UserRepository;
import com.springSec.h2Auth.security.config.PasswordConfig;
import com.springSec.h2Auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    private final PasswordConfig passwordConfig;

    @Autowired
    public UserDetailsServiceImpl(PasswordConfig passwordConfig) {
        this.passwordConfig = passwordConfig;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

}
