package com.shubham.h2_demo.repository;

import com.shubham.h2_demo.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {
    public Authorities findByAuthority(String authority);
}
