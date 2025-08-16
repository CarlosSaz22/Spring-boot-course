package com.banco.electronico.commands.controllers;

import com.banco.electronico.commonapi.commands.CreateAccountCommand;
import com.banco.electronico.commonapi.commands.CreditAccountCommand;
import com.banco.electronico.commonapi.commands.DebitAccountCommand;
import com.banco.electronico.commonapi.dtos.CreateAccountRequestDTO;
import com.banco.electronico.commonapi.dtos.CreditAccountRequestDTO;
import com.banco.electronico.commonapi.dtos.DebitAccountRequestDTO;
import com.banco.electronico.commonapi.enums.AccountStatus;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping("/commands/account")
public class AccountCommandController {

    private final CommandGateway commandGateway;


    @PostMapping("create")
    public CompletableFuture<String> crearNuevaCuenta(@RequestBody CreateAccountRequestDTO createAccountRequestDTO){
        return commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                createAccountRequestDTO.getCurrency(),
                createAccountRequestDTO.getInitialBalance()
        ));
    }

    @PostMapping("credit")
    public CompletableFuture<String> credito(@RequestBody CreditAccountRequestDTO creditAccountRequestDTO){
        return commandGateway.send(new CreditAccountCommand(
                UUID.randomUUID().toString(),
                creditAccountRequestDTO.getCurrency(),
                creditAccountRequestDTO.getAmount()
        ));
    }

    @PostMapping("debit")
    public CompletableFuture<String> debito(@RequestBody DebitAccountRequestDTO debitAccountRequestDTO){
        return commandGateway.send(new DebitAccountCommand(
                UUID.randomUUID().toString(),
                debitAccountRequestDTO.getCurrency(),
                debitAccountRequestDTO.getAmount()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
