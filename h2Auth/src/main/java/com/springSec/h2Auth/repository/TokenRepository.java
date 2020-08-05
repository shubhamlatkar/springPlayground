package com.springSec.h2Auth.repository;

import com.springSec.h2Auth.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    public Optional<Token> findByToken(String token);
    public Boolean existsByToken(String token);
}