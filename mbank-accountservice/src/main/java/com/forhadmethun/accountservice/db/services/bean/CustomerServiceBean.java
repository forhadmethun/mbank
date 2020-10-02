package com.forhadmethun.accountservice.db.services.bean;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Customer;
import com.forhadmethun.accountservice.db.repository.AccountRepository;
import com.forhadmethun.accountservice.db.repository.CustomerRepository;
import com.forhadmethun.accountservice.db.services.AccountService;
import com.forhadmethun.accountservice.db.services.CustomerService;
import com.forhadmethun.accountservice.utility.dto.model.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceBean implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceBean(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
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
