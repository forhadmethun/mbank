package com.forhadmethun.accountservice.controller;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.services.AccountService;
import com.forhadmethun.accountservice.db.services.BalanceService;
import com.forhadmethun.accountservice.db.services.CustomerService;
import com.forhadmethun.accountservice.utility.CurrencyUtil;
import com.forhadmethun.accountservice.utility.dto.mapper.AccountMapper;
import com.forhadmethun.accountservice.utility.dto.mapper.BalanceMapper;
import com.forhadmethun.accountservice.utility.dto.mapper.CustomerMapper;
import com.forhadmethun.accountservice.utility.dto.model.CustomerDto;
import com.forhadmethun.accountservice.utility.exception.PersistenceException;
import com.forhadmethun.accountservice.utility.exception.RequestException;
import com.forhadmethun.accountservice.utility.io.AccountOperationResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Value("${topic.exchange}")
    private String EXCHANGE;

    @Value("${exchange.queue.account.routing.key}")
    public String ROUTING_KEY_ACCOUNT;

    private final CustomerService customerService;
    private final AccountService accountService;
    private final BalanceService balanceService;
    private final RabbitTemplate rabbitTemplate;

    public AccountController(
            CustomerService customerService,
            AccountService accountService,
            BalanceService balanceService,
            RabbitTemplate rabbitTemplate) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.balanceService = balanceService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/accounts")
    public ResponseEntity<AccountOperationResponse> createAccount(
            @RequestBody CustomerDto customerDto
    ) throws RequestException {
        CurrencyUtil.checkCurrencyValidity(customerDto.getCurrencies());
        var customer = customerService.
                createCustomer(CustomerMapper.toCustomer(customerDto));
        var account = accountService.
                createAccount(AccountMapper.toAccountFromCustomerDto(customerDto));
        var balances = balanceService.saveBalance(
                BalanceMapper.toBalancesFromCurrencies(
                        customerDto.getCurrencies(),
                        account.getAccountId()));
        var accountCreationResponse =
                AccountOperationResponse.createAccountCreationResponse(
                        account.getAccountId(),
                        customer.getCustomerId(),
                        BalanceMapper.toBalanceDtoList(balances)
                );
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_ACCOUNT, accountCreationResponse);
        return new ResponseEntity<>(accountCreationResponse, HttpStatus.OK);
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<AccountOperationResponse> getAccount(
            @PathVariable Long accountId) throws PersistenceException {
        var account =  accountService.findByAccountId(accountId);
        var balances = balanceService.findByAccountId(accountId);
        var accountCreationResponse =
                AccountOperationResponse.createAccountCreationResponse(
                        account.getAccountId(),
                        account.getCustomerId(),
                        BalanceMapper.toBalanceDtoList(balances)
                );
        return new ResponseEntity<>(accountCreationResponse, HttpStatus.OK);
    }
}
