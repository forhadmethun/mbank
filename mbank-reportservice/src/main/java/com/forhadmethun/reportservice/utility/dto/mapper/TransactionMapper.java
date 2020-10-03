package com.forhadmethun.reportservice.utility.dto.mapper;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.reportservice.db.entity.Transaction;
import com.forhadmethun.reportservice.utility.dto.model.TransactionDto;

public class TransactionMapper {
    public static Transaction toTransaction(TransactionDto transactionDto){
        return Transaction.builder()
                .transactionId(transactionDto.getTransactionId())
                .accountId(transactionDto.getAccountId())
                .amount(transactionDto.getAmount())
                .currency(transactionDto.getCurrency())
                .directionOfTransaction(transactionDto.getDirectionOfTransaction())
                .description(transactionDto.getDescription())
                .build();
    }
    public static TransactionDto toTransactionDto(Transaction transaction){
        return TransactionDto.builder()
                .transactionId(transaction.getTransactionId())
                .accountId(transaction.getAccountId())
                .amount(transaction.getAmount())
                .currency(transaction.getCurrency())
                .directionOfTransaction(transaction.getDirectionOfTransaction())
                .description(transaction.getDescription())
                .build();
    }
}
