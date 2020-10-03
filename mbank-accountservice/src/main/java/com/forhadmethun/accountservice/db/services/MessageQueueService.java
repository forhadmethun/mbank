package com.forhadmethun.accountservice.db.services;

import com.forhadmethun.accountservice.utility.dto.model.TransactionDto;
import com.forhadmethun.accountservice.utility.io.AccountCreationInfo;
import com.forhadmethun.accountservice.utility.io.AccountOperationResponse;
import com.forhadmethun.accountservice.utility.io.TransactionCreationInfo;

/**
 * @author Md Forhad Hossain
 * @since 04/10/20
 */


public interface MessageQueueService {
    void publishCreateAccount(AccountCreationInfo accountCreationInfo);

    void publishCreateTransaction(TransactionCreationInfo transactionCreationInfo);
}
