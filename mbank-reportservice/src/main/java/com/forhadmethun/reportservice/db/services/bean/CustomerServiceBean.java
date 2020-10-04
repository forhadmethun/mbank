package com.forhadmethun.reportservice.db.services.bean;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.reportservice.db.repository.CustomerRepository;
import com.forhadmethun.reportservice.db.services.AccountService;
import com.forhadmethun.reportservice.db.services.BalanceService;
import com.forhadmethun.reportservice.db.services.CustomerService;
import com.forhadmethun.reportservice.utility.io.AccountCreationInfo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerServiceBean implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AccountService accountService;
    private final BalanceService balanceService;

    public CustomerServiceBean(
            CustomerRepository customerRepository,
            AccountService accountService,
            BalanceService balanceService
    ) {
        this.customerRepository = customerRepository;
        this.accountService = accountService;
        this.balanceService = balanceService;
    }

    @Transactional
    @Override
    public void createCustomer(AccountCreationInfo accountCreationInfo) {
        customerRepository.save(accountCreationInfo.getCustomer());
        accountService.createAccount(accountCreationInfo.getAccount());
        balanceService.saveBalance(accountCreationInfo.getBalances());
    }
}
