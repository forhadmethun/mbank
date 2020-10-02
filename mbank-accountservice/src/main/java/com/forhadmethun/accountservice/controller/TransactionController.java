package com.forhadmethun.accountservice.controller;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.services.AccountService;
import com.forhadmethun.accountservice.db.services.BalanceService;
import com.forhadmethun.accountservice.db.services.TransactionService;
import com.forhadmethun.accountservice.utility.TransactionUtil;
import com.forhadmethun.accountservice.utility.dto.mapper.TransactionMapper;
import com.forhadmethun.accountservice.utility.dto.model.TransactionDto;
import com.forhadmethun.accountservice.utility.exception.PersistenceException;
import com.forhadmethun.accountservice.utility.exception.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    private final AccountService accountService;
    private final BalanceService balanceService;
    private final TransactionService transactionService;

    public TransactionController(
            AccountService accountService,
            BalanceService balanceService,
            TransactionService transactionService) {
        this.accountService = accountService;
        this.balanceService = balanceService;
        this.transactionService = transactionService;
    }

    @PostMapping("/transaction")
    public ResponseEntity<TransactionDto> createTransaction(
            @RequestBody TransactionDto transactionDto
    ) throws RequestException, PersistenceException {
        var account =  accountService.findByAccountId(transactionDto.getAccountId());
        var balances = balanceService.findByAccountId(account.getAccountId());
        var balanceInTransactionCurrency = balances.stream()
                .filter(balance -> balance.getCurrency().equals(transactionDto.getCurrency()))
                .findFirst();
        TransactionUtil.checkTransactionValidity(transactionDto, balanceInTransactionCurrency);
        TransactionDto transaction = transactionService.createTransaction(
                transactionDto,
                balanceInTransactionCurrency.get());
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
    @GetMapping("/transaction/{accountId}")
    public ResponseEntity<List<TransactionDto>> getTransactions(@PathVariable Long accountId){
        var transactions = transactionService.findByAccountId(accountId);
        return new ResponseEntity<>(TransactionMapper.toTransactionDto(transactions), HttpStatus.OK);
    }
}
