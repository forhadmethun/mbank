package com.forhadmethun.accountservice.db.services;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Balance;
import com.forhadmethun.accountservice.db.entity.Transaction;
import com.forhadmethun.accountservice.utility.dto.model.TransactionDto;

import java.util.List;

public interface TransactionService {
    TransactionDto createTransaction(TransactionDto transaction, Balance balance);
    List<Transaction> findByAccountId(Long accountId);
}
