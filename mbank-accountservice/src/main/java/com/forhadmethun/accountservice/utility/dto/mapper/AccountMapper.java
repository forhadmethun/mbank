package com.forhadmethun.accountservice.utility.dto.mapper;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Account;
import com.forhadmethun.accountservice.db.entity.Customer;
import com.forhadmethun.accountservice.utility.dto.model.CustomerDto;

public class AccountMapper {
    public static Account toAccountFromCustomer(Customer customer){
        return Account.builder()
                .customerId(customer.getCustomerId())
                .build();
    }
}
