package com.spring_boot.crud.repository;

import com.spring_boot.crud.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByRole(String role);
}
