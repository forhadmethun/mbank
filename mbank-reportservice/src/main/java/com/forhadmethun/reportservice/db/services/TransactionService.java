package com.forhadmethun.reportservice.db.services;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.reportservice.utility.io.TransactionCreationInfo;

public interface TransactionService {
    void createTransaction(TransactionCreationInfo transactionCreationInfo);
}
