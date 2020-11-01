package com.anz.wholesale.accounts.repository.entity;

import com.anz.wholesale.accounts.models.AccountTypeEnum;
import com.anz.wholesale.common.models.CurrencyEnum;
import com.anz.wholesale.customers.repository.entity.CustomerEntity;
import com.anz.wholesale.transactions.repository.entity.TransactionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNT")
public class AccountEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "ACCOUNT_NAME")
    private String accountName;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name="CUSTOMER_ID")
    private CustomerEntity customer;

    @Column(name = "ACCOUNT_TYPE")
    private AccountTypeEnum accountType;

    @Column(name = "BALANCE_DATE")
    private Timestamp balanceDate;

    @Column(name = "CURRENCY")
    private CurrencyEnum currency;

    @Column(name = "AVAILABLE_BALANCE")
    private BigDecimal availableBalance;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<TransactionEntity> transactions;

}