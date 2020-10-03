package com.forhadmethun.accountservice.controller;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */


import com.forhadmethun.accountservice.db.entity.Transaction;
import com.forhadmethun.accountservice.db.services.TransactionService;
import com.forhadmethun.accountservice.utility.dto.mapper.TransactionMapper;
import com.forhadmethun.accountservice.utility.dto.model.TransactionDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transaction")
    @Transactional
    public ResponseEntity<TransactionDto> createTransaction(
            @RequestBody TransactionDto transactionDto
    ){
        TransactionDto transactionResponse = transactionService.createTransaction(transactionDto);

        return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
    }
    @GetMapping("/transaction/{accountId}")
    public ResponseEntity<List<TransactionDto>> getTransactions(
            @PathVariable Long accountId
    ){
        List<Transaction> transactions = transactionService.findByAccountId(accountId);

        return new ResponseEntity<>(TransactionMapper.toTransactionDto(transactions), HttpStatus.OK);
    }
}
