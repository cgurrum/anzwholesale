package com.anz.wholesale.transactions.service;

import com.anz.wholesale.transactions.models.Transaction;
import com.anz.wholesale.transactions.repository.TransactionsRepository;
import com.anz.wholesale.transactions.repository.entity.TransactionEntity;
import com.anz.wholesale.transactions.utils.TransactionConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionsService {
    private final TransactionsRepository repository;
    private final TransactionConverter converter;

    public List<Transaction> getAccountTransactions(String customerId, String accountNumber) {
        log.debug("TransactionsService: getAccountTransactions for customer: {} with accountNumber: {}",customerId,accountNumber);
        List<TransactionEntity> transactions =
                repository.findByAccount_Customer_IdAndAccount_AccountNumberOrderByValueDateDesc(customerId, accountNumber);
        log.info("TransactionsService: getAccountTransactions for customer: {} with accountNumber: {} returns {} records",customerId, accountNumber,transactions.size());
        return transactions.stream().map(converter::covert).collect(Collectors.toList());
    }
}
