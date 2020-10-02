package com.forhadmethun.accountservice.utility.dto.mapper;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Balance;
import com.forhadmethun.accountservice.utility.dto.model.BalanceDto;

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

    public static List<BalanceDto> toBalanceDtoList(List<Balance> balance) {
        return balance.stream()
                .map(BalanceMapper::toBalanceDto)
                .collect(Collectors.toList());
    }
}
