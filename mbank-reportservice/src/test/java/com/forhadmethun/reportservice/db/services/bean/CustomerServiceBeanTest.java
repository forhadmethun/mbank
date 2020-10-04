package com.forhadmethun.reportservice.db.services.bean;

import com.forhadmethun.reportservice.db.entity.Account;
import com.forhadmethun.reportservice.db.entity.Balance;
import com.forhadmethun.reportservice.db.entity.Customer;
import com.forhadmethun.reportservice.db.repository.AccountRepository;
import com.forhadmethun.reportservice.db.repository.BalanceRepository;
import com.forhadmethun.reportservice.db.repository.CustomerRepository;
import com.forhadmethun.reportservice.db.services.CustomerService;
import com.forhadmethun.reportservice.utility.io.AccountCreationInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerServiceBeanTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BalanceRepository balanceRepository;

    @Test
    void createCustomer() {
        AccountCreationInfo accountCreationInfo =
                AccountCreationInfo.builder()
                        .customer(
                                Customer.builder()
                                        .country("Bangladesh")
                                        .customerId(1L)
                                        .build()
                        )
                        .account(
                                Account.builder()
                                        .customerId(1L)
                                        .accountId(1L)
                                        .build()
                        )
                        .balances(
                                Arrays.asList(
                                        Balance.builder()
                                                .balanceId(1L)
                                                .accountId(1L)
                                                .currency("EUR")
                                                .balance(BigDecimal.valueOf(50L))
                                                .build()
                                )
                        )
                        .build();
        customerService.createCustomer(accountCreationInfo);
        Optional<Customer> insertedCustomer = customerRepository.findById(1L);
        Account insertedAccount = accountRepository.findByAccountId(1L);
        List<Balance> insertedBalances = balanceRepository.findByAccountId(1L);
        assertEquals(accountCreationInfo.getAccount().getAccountId(), insertedAccount.getAccountId());
        assertEquals(accountCreationInfo.getCustomer().getCustomerId(), insertedCustomer.get().getCustomerId());
        assertEquals(accountCreationInfo.getBalances().get(0).getCurrency(), insertedBalances.get(0).getCurrency());
    }
}
