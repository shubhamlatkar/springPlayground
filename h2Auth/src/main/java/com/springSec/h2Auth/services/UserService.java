package com.springSec.h2Auth.services;

import com.springSec.h2Auth.model.User;
import com.springSec.h2Auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUser(String username) {
        System.out.println(userRepository.findByUsername(username).orElse(null));
        return userRepository.findByUsername(username);
    }
}
