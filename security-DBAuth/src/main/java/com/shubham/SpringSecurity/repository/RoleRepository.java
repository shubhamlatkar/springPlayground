package com.shubham.SpringSecurity.repository;


import com.shubham.SpringSecurity.model.ERole;
import com.shubham.SpringSecurity.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
