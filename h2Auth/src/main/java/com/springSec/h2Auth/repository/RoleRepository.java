package com.springSec.h2Auth.repository;

import com.springSec.h2Auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByRole(String role);
}