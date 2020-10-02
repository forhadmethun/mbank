package com.forhadmethun.accountservice.db.services.bean;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Account;
import com.forhadmethun.accountservice.db.repository.AccountRepository;
import com.forhadmethun.accountservice.db.services.AccountService;
import com.forhadmethun.accountservice.utility.constant.PersistenceConstant;
import com.forhadmethun.accountservice.utility.exception.PersistenceException;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceBean implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceBean(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account findByAccountId(Long accountId) throws PersistenceException {
        var account = accountRepository.findByAccountId(accountId);
        if (account == null)
            throw new PersistenceException(PersistenceConstant.ACCOUNT_NOT_FOUND);
        return account;
    }
/*
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = customerRepository.save(CustomerMapper.toCustomer(customerDto));
        accountRepository.save(
                Account.builder()
                        .customerId(customer.getCustomerId())
                        .build()
        );
        saveCurrencies(customerDto.getCurrencies(), customer);

    }
    private void saveCurrencies(List<String> currencies, Customer customer){
        //validate
        currencies.stream().forEach(
                currency -> customerCurrencyRepository
                        .save(
                                CustomerCurrency.builder()
                                        .currencyCode(currency)
                                        .customer(customer)
                                        .build()
                        ));

    }*/
}
