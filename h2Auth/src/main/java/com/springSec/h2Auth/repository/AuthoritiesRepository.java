package com.springSec.h2Auth.repository;

import com.springSec.h2Auth.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities,Long> {
    public Authorities findByAuthority(String authority);
}
