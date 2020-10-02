package com.forhadmethun.accountservice.utility.dto.mapper;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Transaction;
import com.forhadmethun.accountservice.utility.dto.model.TransactionDto;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionMapper {
    public static Transaction toTransaction(TransactionDto transactionDto){
        return Transaction.builder()
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
    public static List<TransactionDto> toTransactionDto(List<Transaction> transactions){
        return transactions.stream()
                .map(TransactionMapper::toTransactionDto)
                .collect(Collectors.toList());
    }
}
