package com.shubham.SpringSecurity.repository;

import com.shubham.SpringSecurity.document.Authorities;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthoritiesRepository extends MongoRepository<Authorities, String> {
    public Authorities findByAuthority(String authority);
}
