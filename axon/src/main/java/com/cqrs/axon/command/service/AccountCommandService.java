package com.cqrs.axon.command.service;

import com.cqrs.axon.command.commands.CreateAccountCommand;
import com.cqrs.axon.command.commands.DepositMoneyCommand;
import com.cqrs.axon.command.commands.WithdrawMoneyCommand;
import com.cqrs.axon.command.dto.CreateAccountRequest;
import com.cqrs.axon.command.dto.DepositRequest;
import com.cqrs.axon.command.dto.WithdrawalRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AccountCommandService {

    private final CommandGateway commandGateway;

    public AccountCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> createAccount(CreateAccountRequest createAccountRequest) {
        return commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                createAccountRequest.getStartingBalance())
        );
    }

    public CompletableFuture<String> depositToAccount(DepositRequest depositRequest) {
        return commandGateway.send(new DepositMoneyCommand(
                depositRequest.getAccountId(),
                depositRequest.getAmount()
        ));
    }

    public CompletableFuture<String> withdrawFromAccount(WithdrawalRequest withdrawalRequest) {
        return commandGateway.send(new WithdrawMoneyCommand(
                withdrawalRequest.getAccountId(),
                withdrawalRequest.getAmount()
        ));
    }
}