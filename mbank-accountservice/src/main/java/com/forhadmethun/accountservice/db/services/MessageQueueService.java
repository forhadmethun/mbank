package com.forhadmethun.accountservice.db.services;

import com.forhadmethun.accountservice.utility.io.mq.AccountCreationInfo;
import com.forhadmethun.accountservice.utility.io.mq.TransactionCreationInfo;

/**
 * @author Md Forhad Hossain
 * @since 04/10/20
 */


public interface MessageQueueService {
    void publishCreateAccount(AccountCreationInfo accountCreationInfo);

    void publishCreateTransaction(TransactionCreationInfo transactionCreationInfo);
}
