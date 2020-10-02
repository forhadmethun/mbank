package com.forhadmethun.reportservice.db.services;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.reportservice.db.entity.Balance;
import com.forhadmethun.reportservice.utility.dto.model.TransactionDto;

public interface TransactionService {
    TransactionDto createTransaction(TransactionDto transaction, Balance balance);
}
