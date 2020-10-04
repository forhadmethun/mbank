package com.forhadmethun.accountservice.utility.dto.mapper;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Account;
import com.forhadmethun.accountservice.db.entity.Customer;
import com.forhadmethun.accountservice.utility.dto.model.AccountDto;

public class AccountMapper {
    public static Account toAccountFromCustomer(Customer customer){
        return Account.builder()
                .customer(customer)
                .build();
    }

    public static AccountDto toAccountDto(Account account, Long customerId) {
        return AccountDto.builder()
                .accountId(account.getAccountId())
                .customerId(customerId)
                .build();
    }

    public static Account toAccount(AccountDto account) {
        return Account.builder()
                .accountId(account.getAccountId())
                .build();
    }
}
