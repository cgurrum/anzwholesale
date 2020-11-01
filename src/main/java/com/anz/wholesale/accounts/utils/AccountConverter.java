package com.anz.wholesale.accounts.utils;

import com.anz.wholesale.accounts.models.Account;
import com.anz.wholesale.accounts.repository.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {
    public Account covert(AccountEntity accountEntity){
        Account account = new Account();
        account.setAccountNumber(accountEntity.getAccountNumber());
        account.setAccountName(accountEntity.getAccountName());
        account.setAccountType(accountEntity.getAccountType());
        account.setCurrency(accountEntity.getCurrency());
        account.setAvailableBalance(accountEntity.getAvailableBalance());
        account.setBalanceDate(accountEntity.getBalanceDate());
        return account;
    }
}
