package com.shubham.security_jwt.repository;

import com.shubham.security_jwt.document.Authorities;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthoritiesRepository extends MongoRepository<Authorities, String> {
    public Authorities findByAuthority(String authority);
}
