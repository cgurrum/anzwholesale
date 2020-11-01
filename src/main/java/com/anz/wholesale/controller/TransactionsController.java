package com.anz.wholesale.controller;

import com.anz.wholesale.transactions.models.Transaction;
import com.anz.wholesale.transactions.service.TransactionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('SCP_ACCOUNTS')")
public class TransactionsController {
    private final TransactionsService transactionsService;

    @GetMapping(value = "/accounts/{accountNumber}/transactions", produces = "application/json")
    public List<Transaction> getTransactions(Principal principal, @PathVariable String accountNumber) {
        log.debug("TransactionsController: getTransactions for customer: {} with accountNumber: {}",principal.getName(),accountNumber);
        return this.transactionsService.getAccountTransactions(principal.getName(), accountNumber);
    }
}
