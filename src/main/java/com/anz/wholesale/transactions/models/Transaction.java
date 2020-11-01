package com.anz.wholesale.transactions.models;

import com.anz.wholesale.common.models.CurrencyEnum;
import com.anz.wholesale.common.jsonserializer.MoneySerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {

    @JsonIgnore
    private String id;

    private String accountNumber;

    private String accountName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy")
    private Date valueDate;

    private CurrencyEnum currency;

    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal debitAmount;

    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal creditAmount;

    private TransactionTypeEnum transactionType;

    private String transactionDescription;
}