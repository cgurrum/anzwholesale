package com.anz.wholesale.controller;

import com.anz.wholesale.accounts.models.Account;
import com.anz.wholesale.accounts.service.AccountsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('SCP_ACCOUNTS')")
public class AccountsController {
    private final AccountsService accountsService;

    @GetMapping(value = "/accounts", produces = "application/json")
    public List<Account> getAccounts(Principal principal) {
        log.debug("AccountsController: getAccounts for customer: {}", principal.getName());
        return this.accountsService.getUserAccounts(principal.getName());
    }
}
