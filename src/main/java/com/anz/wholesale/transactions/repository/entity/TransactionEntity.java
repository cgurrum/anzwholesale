package com.anz.wholesale.transactions.repository.entity;

import com.anz.wholesale.accounts.repository.entity.AccountEntity;
import com.anz.wholesale.common.models.CurrencyEnum;
import com.anz.wholesale.transactions.models.TransactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRANSACTION")
public class TransactionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private String id;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="ACCOUNT_NUMBER")
    private AccountEntity account;

    @Column(name = "VALUE_DATE")
    private Timestamp valueDate;

    @Column(name = "CURRENCY")
    private CurrencyEnum currency;

    @Column(name = "DEBIT_AMOUNT")
    private BigDecimal debitAmount;

    @Column(name = "CREDIT_AMOUNT")
    private BigDecimal creditAmount;

    @Column(name = "TRANSACTION_TYPE")
    private TransactionTypeEnum transactionType;

    @Column(name = "TRANSACTION_DESCRIPTION")
    private String transactionDescription;
}