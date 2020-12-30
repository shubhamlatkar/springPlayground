package com.springexample.logging.repository;

import com.springexample.logging.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
