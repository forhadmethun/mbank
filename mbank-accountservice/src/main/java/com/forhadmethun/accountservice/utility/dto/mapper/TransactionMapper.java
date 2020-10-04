package com.forhadmethun.accountservice.utility.dto.mapper;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Balance;
import com.forhadmethun.accountservice.db.entity.Transaction;
import com.forhadmethun.accountservice.utility.dto.model.TransactionDto;


public class TransactionMapper {
    public static Transaction toTransaction(TransactionDto transactionDto, Balance balance){
        return Transaction.builder()
                .account(balance.getAccount())
                .amount(transactionDto.getAmount())
                .currency(transactionDto.getCurrency())
                .directionOfTransaction(transactionDto.getDirectionOfTransaction())
                .description(transactionDto.getDescription())
                .build();
    }
    public static TransactionDto toTransactionDto(Transaction transaction){
        return TransactionDto.builder()
                .transactionId(transaction.getTransactionId())
                .accountId(transaction.getAccount().getAccountId())
                .amount(transaction.getAmount())
                .currency(transaction.getCurrency())
                .directionOfTransaction(transaction.getDirectionOfTransaction())
                .description(transaction.getDescription())
                .build();
    }

    public static Transaction toNewTransaction(Transaction transaction){
        return Transaction.builder()
                .account(transaction.getAccount())
                .amount(transaction.getAmount())
                .currency(transaction.getCurrency())
                .directionOfTransaction(transaction.getDirectionOfTransaction())
                .description(transaction.getDescription())
                .build();
    }
}
