package com.shubham.security_jwt.repository;

import com.shubham.security_jwt.document.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    public Role findByRole(String role);
}
