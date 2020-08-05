package com.shubham.security_jwt.repository;

import com.shubham.security_jwt.document.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByRole(String role);
}
