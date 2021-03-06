package com.shubham.SpringSecurity.repository;

import com.shubham.SpringSecurity.document.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    public Role findByRole(String role);
}
