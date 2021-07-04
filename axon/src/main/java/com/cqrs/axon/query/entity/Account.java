package com.cqrs.axon.query.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class Account {

    @Id
    private String accountId;
    private BigDecimal balance;
    private String status;
}