package com.forhadmethun.accountservice.db.services.bean;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Account;
import com.forhadmethun.accountservice.db.entity.Balance;
import com.forhadmethun.accountservice.db.entity.Customer;
import com.forhadmethun.accountservice.db.repository.CustomerRepository;
import com.forhadmethun.accountservice.db.services.AccountService;
import com.forhadmethun.accountservice.db.services.BalanceService;
import com.forhadmethun.accountservice.db.services.CustomerService;
import com.forhadmethun.accountservice.db.services.MessageQueueService;
import com.forhadmethun.accountservice.utility.dto.mapper.AccountMapper;
import com.forhadmethun.accountservice.utility.dto.mapper.BalanceMapper;
import com.forhadmethun.accountservice.utility.dto.mapper.CustomerMapper;
import com.forhadmethun.accountservice.utility.dto.model.CustomerDto;
import com.forhadmethun.accountservice.utility.io.mq.AccountCreationInfo;
import com.forhadmethun.accountservice.utility.io.AccountOperationResponse;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class CustomerServiceBean implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AccountService accountService;
    private final BalanceService balanceService;
    private final MessageQueueService messageQueueService;

    @Transactional
    @Override
    public AccountOperationResponse createCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.save(CustomerMapper.toCustomer(customerDto));

        Account account = accountService.
                createAccount(AccountMapper.toAccountFromCustomer(customer));

        List<Balance> balances = balanceService.saveBalance(
                BalanceMapper.toBalancesFromCurrencies(
                        customerDto.getCurrencies(),
                        account));

        AccountOperationResponse accountCreationResponse =
                AccountOperationResponse.createAccountCreationResponse(
                        account.getAccountId(),
                        customerDto.getCustomerId(),
                        BalanceMapper.toBalanceDtoList(balances)
                );
        messageQueueService.publishCreateAccount(
                AccountCreationInfo.builder()
                        .customer(CustomerMapper.toCustomerDto(customer))
                        .account(AccountMapper.toAccountDto(account, customer.getCustomerId()))
                        .balances(BalanceMapper.toBalanceDtoList(balances, account.getAccountId()))
                        .build()
        );
        return accountCreationResponse;
    }

    @Override
    public Customer findByCustomerId(Long customerId) {
        return customerRepository.findByCustomerId(customerId);
    }
}
