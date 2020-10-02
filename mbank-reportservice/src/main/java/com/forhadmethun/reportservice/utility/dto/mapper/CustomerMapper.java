package com.forhadmethun.reportservice.utility.dto.mapper;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.reportservice.db.entity.Customer;
import com.forhadmethun.reportservice.utility.dto.model.CustomerDto;

public class CustomerMapper {
    public static Customer toCustomer(CustomerDto customer){
        return Customer.builder()
                .customerId(customer.getCustomerId())
                .country(customer.getCountry())
                .build();
    }
}
