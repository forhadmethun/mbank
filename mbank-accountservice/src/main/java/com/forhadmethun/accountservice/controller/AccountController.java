package com.forhadmethun.accountservice.controller;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.services.AccountService;
import com.forhadmethun.accountservice.db.services.CustomerService;
import com.forhadmethun.accountservice.utility.CurrencyUtil;
import com.forhadmethun.accountservice.utility.dto.model.CustomerDto;
import com.forhadmethun.accountservice.utility.exception.PersistenceException;
import com.forhadmethun.accountservice.utility.exception.RequestException;
import com.forhadmethun.accountservice.utility.io.AccountOperationResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    private final CustomerService customerService;
    private final AccountService accountService;

    public AccountController(
            CustomerService customerService,
            AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @PostMapping("/accounts")
    public ResponseEntity<AccountOperationResponse> createAccount(
            @RequestBody CustomerDto customerDto
    ) throws RequestException {
        CurrencyUtil.checkCurrencyValidity(customerDto.getCurrencies());

        AccountOperationResponse accountCreationResponse =
                customerService.createCustomer(customerDto);

        return new ResponseEntity<>(accountCreationResponse, HttpStatus.OK);
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<AccountOperationResponse> getAccount(
            @PathVariable Long accountId
    ) throws PersistenceException {
        AccountOperationResponse accountOperationResponse =
                accountService.findByAccountId(accountId);

        return new ResponseEntity<>(accountOperationResponse, HttpStatus.OK);
    }
}
