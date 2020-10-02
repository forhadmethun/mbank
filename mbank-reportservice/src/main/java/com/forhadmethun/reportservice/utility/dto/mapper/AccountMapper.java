package com.forhadmethun.reportservice.utility.dto.mapper;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.reportservice.db.entity.Account;
import com.forhadmethun.reportservice.utility.dto.model.CustomerDto;

public class AccountMapper {
    public static Account toAccountFromCustomerDto(CustomerDto customer){
        return Account.builder()
                .customerId(customer.getCustomerId())
                .build();
    }
}
