package com.cqrs.axon.command.aggregate;

import com.cqrs.axon.command.commands.CreateAccountCommand;
import com.cqrs.axon.command.commands.DepositMoneyCommand;
import com.cqrs.axon.command.commands.WithdrawMoneyCommand;
import com.cqrs.axon.common.event.AccountActivatedEvent;
import com.cqrs.axon.common.event.AccountCreatedEvent;
import com.cqrs.axon.common.event.AccountCreditedEvent;
import com.cqrs.axon.common.event.AccountDebitedEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
@Slf4j
@Data
public class AccountAggregate {

    @AggregateIdentifier
    private String accountId;
    private BigDecimal balance;
    private String status;

    public AccountAggregate() {
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        log.info("CreateAccountCommand received.");
        AggregateLifecycle.apply(new AccountCreatedEvent(
                createAccountCommand.getId(),
                createAccountCommand.getBalance()));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent accountCreatedEvent) {
        log.info("An AccountCreatedEvent occurred.");
        this.accountId = accountCreatedEvent.getId();
        this.balance = accountCreatedEvent.getBalance();
        this.status = "CREATED";

        AggregateLifecycle.apply(new AccountActivatedEvent(this.accountId, "ACTIVATED"));
    }

    @EventSourcingHandler
    public void on(AccountActivatedEvent accountActivatedEvent) {
        log.info("An AccountActivatedEvent occurred.");
        this.status = accountActivatedEvent.getStatus();
    }

    @CommandHandler
    public void on(DepositMoneyCommand depositMoneyCommand) {
        log.info("DepositMoneyCommand received.");
        AggregateLifecycle.apply(new AccountCreditedEvent(
                depositMoneyCommand.getId(),
                depositMoneyCommand.getAmount()));
    }

    @EventSourcingHandler
    public void on(AccountCreditedEvent accountCreditedEvent) {
        log.info("An AccountCreditedEvent occurred.");
        this.balance = this.balance.add(accountCreditedEvent.getAmount());
    }

    @CommandHandler
    public void on(WithdrawMoneyCommand withdrawMoneyCommand) {
        log.info("WithdrawMoneyCommand received.");
        AggregateLifecycle.apply(new AccountDebitedEvent(
                withdrawMoneyCommand.getId(),
                withdrawMoneyCommand.getAmount()));
    }

    @EventSourcingHandler
    public void on(AccountDebitedEvent accountDebitedEvent) {
        log.info("An AccountDebitedEvent occurred.");
        this.balance = this.balance.subtract(accountDebitedEvent.getAmount());
    }
}