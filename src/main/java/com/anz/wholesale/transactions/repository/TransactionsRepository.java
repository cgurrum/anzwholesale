package com.anz.wholesale.transactions.repository;

import com.anz.wholesale.transactions.repository.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<TransactionEntity, String > {
    List<TransactionEntity> findByAccount_Customer_IdAndAccount_AccountNumberOrderByValueDateDesc(String customerId, String accountNumber);
}
