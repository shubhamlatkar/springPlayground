package com.cqrs.axon.query.repository;

import com.cqrs.axon.query.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author BridgingCode - AJ Catambay
 **/
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}