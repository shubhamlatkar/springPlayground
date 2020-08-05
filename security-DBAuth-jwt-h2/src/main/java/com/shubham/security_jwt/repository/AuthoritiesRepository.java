package com.shubham.security_jwt.repository;

import com.shubham.security_jwt.document.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {
    public Authorities findByAuthority(String authority);
}
