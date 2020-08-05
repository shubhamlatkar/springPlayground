package com.shubham.security_jwt.repository;

import com.shubham.security_jwt.document.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Tokens, Long> {
    public Optional<Tokens> findByToken(String token);
    public Boolean existsByToken(String Token);
}
