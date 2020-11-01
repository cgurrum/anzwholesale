package com.anz.wholesale.accounts.service;

import com.anz.wholesale.accounts.models.Account;
import com.anz.wholesale.accounts.repository.AccountsRepository;
import com.anz.wholesale.accounts.repository.entity.AccountEntity;
import com.anz.wholesale.accounts.utils.AccountConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountsService {
    private final AccountsRepository repository;
    private final AccountConverter converter;

    public List<Account> getUserAccounts(String customerId) {
        log.debug("AccountService: getUserAccounts for customer: {}",customerId);
        List<AccountEntity> accounts = repository.findByCustomer_Id(customerId);
        log.info("AccountService: getUserAccounts for customer: {} returns {} records",customerId,accounts.size());
        return accounts.stream().map(converter::covert).collect(Collectors.toList());
    }
}
