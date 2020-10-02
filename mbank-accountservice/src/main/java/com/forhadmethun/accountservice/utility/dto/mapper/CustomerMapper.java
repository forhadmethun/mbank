package com.forhadmethun.accountservice.utility.dto.mapper;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Customer;
import com.forhadmethun.accountservice.utility.dto.model.CustomerDto;

public class CustomerMapper {
    public static Customer toCustomer(CustomerDto customer){
        return Customer.builder()
                .customerId(customer.getCustomerId())
                .country(customer.getCountry())
                .build();
    }
}
