package com.banco.electronico.query.controllers;

import com.banco.electronico.query.dto.AccountWatchEvent;
import com.banco.electronico.query.entities.Account;
import com.banco.electronico.query.queries.GetAccountByid;
import com.banco.electronico.query.queries.GetAllAccounts;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/query/account")
@AllArgsConstructor
public class AccountQueryController {

    private final QueryGateway queryGateway;

    @GetMapping
    public CompletableFuture<List<Account>> findAllAccounts() {
        return queryGateway.query(new GetAllAccounts(), ResponseTypes.multipleInstancesOf(Account.class));
    }

    @GetMapping("/byId/{id}")
    public CompletableFuture<Account> findAccountById(@PathVariable String id) {
        return queryGateway.query(new GetAccountByid(id), ResponseTypes.instanceOf(Account.class));
    }
/*
    @GetMapping("/{accountId}")
    public CompletableFuture<AccountWatchEvent> accountBalance(@PathVariable String accounId) {
        return queryGateway.query(new Get(accounId), ResponseTypes.instanceOf(Account.class));
    }

 */

}
