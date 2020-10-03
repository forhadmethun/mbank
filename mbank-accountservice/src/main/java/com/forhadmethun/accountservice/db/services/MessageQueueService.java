package com.forhadmethun.accountservice.db.services;

import com.forhadmethun.accountservice.utility.dto.model.TransactionDto;
import com.forhadmethun.accountservice.utility.io.AccountOperationResponse;

/**
 * @author Md Forhad Hossain
 * @since 04/10/20
 */


public interface MessageQueueService {
    void publishCreateAccount(AccountOperationResponse accountOperationResponse);

    void publishCreateTransaction(TransactionDto transactionDto);
}
