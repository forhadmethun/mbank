package com.forhadmethun.reportservice.db.services.bean;

import com.forhadmethun.reportservice.db.entity.Account;
import com.forhadmethun.reportservice.db.entity.Balance;
import com.forhadmethun.reportservice.db.entity.Transaction;
import com.forhadmethun.reportservice.db.repository.AccountRepository;
import com.forhadmethun.reportservice.db.repository.BalanceRepository;
import com.forhadmethun.reportservice.db.repository.CustomerRepository;
import com.forhadmethun.reportservice.db.services.CustomerService;
import com.forhadmethun.reportservice.db.services.TransactionService;
import com.forhadmethun.reportservice.utility.dto.model.DirectionOfTransaction;
import com.forhadmethun.reportservice.utility.io.TransactionCreationInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TransactionServiceBeanTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    TransactionService transactionService;

    @Test
    void createCustomer() {

        TransactionCreationInfo transactionCreationInfo =
                TransactionCreationInfo.builder()
                        .balance(
                                Balance.builder()
                                        .balanceId(1L)
                                        .accountId(1L)
                                        .currency("EUR")
                                        .balance(BigDecimal.valueOf(50L))
                                        .build()
                        )
                        .transaction(
                                Transaction.builder()
                                        .transactionId(1L)
                                        .accountId(1L)
                                        .amount(BigDecimal.valueOf(50L))
                                        .currency("EUR")
                                        .directionOfTransaction(DirectionOfTransaction.IN)
                                        .description("Cash In")
                                    .build()
                        )
                        .build();
        transactionService.createTransaction(transactionCreationInfo);
        Account insertedAccount = accountRepository.findByAccountId(1L);
        List<Transaction> transactions = transactionService.findByAccountId(1L);
        List<Balance> balances = balanceRepository.findByAccountId(1L);
        assertEquals(insertedAccount.getAccountId(), 1L);
        assertEquals(balances.get(0).getCurrency(), "EUR");
        assertEquals(transactions.get(0).getCurrency(), "EUR");
    }
}
