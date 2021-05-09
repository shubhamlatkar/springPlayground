package com.spring_boot.crud.repository;

import com.spring_boot.crud.entity.Aadhaar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AadhaarRepository extends JpaRepository<Aadhaar, Long> {
}
