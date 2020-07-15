package com.shubham.h2_demo.repository;

import com.shubham.h2_demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByRole(String role);
}
