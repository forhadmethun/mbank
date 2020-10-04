package com.forhadmethun.accountservice.utility.dto.mapper;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Account;
import com.forhadmethun.accountservice.db.entity.Balance;
import com.forhadmethun.accountservice.utility.dto.model.BalanceDto;
import com.forhadmethun.accountservice.utility.dto.model.mq.BalanceMQDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BalanceMapper {
    public static List<Balance> toBalancesFromCurrencies(List<String> currencies, Account account) {
        List<Balance> balances = new ArrayList<>();
        currencies.stream()
                .map(currency -> Balance.builder()
                        .account(account)
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

    public static List<BalanceMQDto> toBalanceDtoList(List<Balance> balance, Long accountId) {
        return balance.stream()
                .map(eachBalance -> toBalanceMQDto(eachBalance, accountId))
                .collect(Collectors.toList());
    }

    public static List<BalanceDto> toBalanceDtoList(List<Balance> balance) {
        return balance.stream()
                .map(BalanceMapper::toBalanceDto)
                .collect(Collectors.toList());
    }

    public static BalanceMQDto toBalanceMQDto(Balance balance, Long accountId) {
        return BalanceMQDto.builder()
                .accountId(accountId)
                .balanceId(balance.getBalanceId())
                .balance(balance.getBalance())
                .currency(balance.getCurrency())
                .build();
    }
}
