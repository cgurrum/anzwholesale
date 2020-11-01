package com.anz.wholesale.transactions.utils;

import com.anz.wholesale.transactions.models.Transaction;
import com.anz.wholesale.transactions.repository.entity.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {
    public Transaction covert(TransactionEntity transactionEntity){
        Transaction transaction = new Transaction();
        transaction.setId(transactionEntity.getId());
        transaction.setAccountNumber(transactionEntity.getAccount().getAccountNumber());
        transaction.setAccountName(transactionEntity.getAccount().getAccountName());
        transaction.setValueDate(transactionEntity.getValueDate());
        transaction.setCurrency(transactionEntity.getCurrency());
        transaction.setTransactionType(transactionEntity.getTransactionType());
        transaction.setCreditAmount(transactionEntity.getCreditAmount());
        transaction.setDebitAmount(transactionEntity.getDebitAmount());
        transaction.setTransactionDescription(transactionEntity.getTransactionDescription());
        return transaction;
    }
}
