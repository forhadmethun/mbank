package com.forhadmethun.accountservice.db.services;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.utility.dto.model.CustomerDto;
import com.forhadmethun.accountservice.utility.io.AccountOperationResponse;

public interface CustomerService {
    AccountOperationResponse createCustomer(CustomerDto customerDto);
}
