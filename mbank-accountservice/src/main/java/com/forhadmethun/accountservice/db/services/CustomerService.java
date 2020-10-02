package com.forhadmethun.accountservice.db.services;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Customer;

public interface CustomerService {
    Customer createCustomer(Customer customer);
}
