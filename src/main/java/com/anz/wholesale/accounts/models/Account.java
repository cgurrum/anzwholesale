package com.anz.wholesale.accounts.models;

import com.anz.wholesale.common.models.CurrencyEnum;
import com.anz.wholesale.common.jsonserializer.MoneySerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Account {

    private String accountNumber;

    private String accountName;

    private AccountTypeEnum accountType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date balanceDate;

    private CurrencyEnum currency;

    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal availableBalance;

}