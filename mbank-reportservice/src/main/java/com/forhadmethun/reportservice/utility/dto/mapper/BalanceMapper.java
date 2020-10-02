package com.forhadmethun.reportservice.utility.dto.mapper;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.reportservice.db.entity.Balance;
import com.forhadmethun.reportservice.utility.dto.model.BalanceDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BalanceMapper {
    public static List<Balance> toBalancesFromCurrencies(List<String> currencies, Long accountId) {
        List<Balance> balances = new ArrayList<>();
        currencies.stream()
                .map(currency -> Balance.builder()
                        .accountId(accountId)
                        .currency(currency)
                        .balance(BigDecimal.ZERO)
                        .build())
                .forEach(
                        balances::add
                );
        return balances;

    }

    public static BalanceDto toBalanceDto(Balance balance) {
        return BalanceDto.builder()
                .balance(balance.getBalance())
                .currency(balance.getCurrency())
                .build();
    }
    public static Balance toBalance(BalanceDto balance) {
        return Balance.builder()
                .balance(balance.getBalance())
                .currency(balance.getCurrency())
                .build();
    }
    public static List<Balance> toBalanceList(List<BalanceDto> balanceDtos) {
        return balanceDtos.stream()
                .map(BalanceMapper::toBalance)
                .collect(Collectors.toList());
    }
}
