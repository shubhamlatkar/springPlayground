package com.shubham.SpringSecurity.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.shubham.SpringSecurity.security.ApplicationUserRole.*;

@Repository("fake")
public class ApplicationUserDaoService implements ApplicationUserDao {

    final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> applicationUser.getUsername().equals(username))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        return Lists.newArrayList(
                new ApplicationUser(STUDENT.getGrantedAuthorities(),
                        passwordEncoder.encode("12as"),
                        "knu",
                        true,
                        true,
                        true,
                        true),
                new ApplicationUser(ADMIN.getGrantedAuthorities(),
                        passwordEncoder.encode("12as"),
                        "shu",
                        true,
                        true,
                        true,
                        true),
                new ApplicationUser(ADMINTRAINEE.getGrantedAuthorities(),
                        passwordEncoder.encode("12as"),
                        "ps",
                        true,
                        true,
                        true,
                        true)
                );
    }
}
