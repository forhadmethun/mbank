package com.forhadmethun.reportservice.db.services;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.reportservice.utility.io.AccountCreationInfo;

public interface CustomerService {
    void createCustomer(AccountCreationInfo accountCreationInfo);
}
