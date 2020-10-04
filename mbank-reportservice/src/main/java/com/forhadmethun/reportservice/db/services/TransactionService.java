package com.forhadmethun.reportservice.db.services;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.reportservice.db.entity.Transaction;
import com.forhadmethun.reportservice.utility.io.TransactionCreationInfo;

import java.util.List;

public interface TransactionService {
    void createTransaction(TransactionCreationInfo transactionCreationInfo);
    List<Transaction> findByAccountId(Long accountId);

}
