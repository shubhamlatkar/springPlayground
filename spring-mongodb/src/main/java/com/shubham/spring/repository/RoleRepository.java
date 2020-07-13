package com.shubham.spring.repository;

import com.shubham.spring.document.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    public Role findByRole(String role);
}
