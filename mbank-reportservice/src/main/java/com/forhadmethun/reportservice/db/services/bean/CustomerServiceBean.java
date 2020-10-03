package com.forhadmethun.reportservice.db.services.bean;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.reportservice.db.entity.Account;
import com.forhadmethun.reportservice.db.entity.Balance;
import com.forhadmethun.reportservice.db.entity.Customer;
import com.forhadmethun.reportservice.db.repository.CustomerRepository;
import com.forhadmethun.reportservice.db.services.AccountService;
import com.forhadmethun.reportservice.db.services.BalanceService;
import com.forhadmethun.reportservice.db.services.CustomerService;
import com.forhadmethun.reportservice.utility.dto.mapper.AccountMapper;
import com.forhadmethun.reportservice.utility.dto.mapper.BalanceMapper;
import com.forhadmethun.reportservice.utility.dto.mapper.CustomerMapper;
import com.forhadmethun.reportservice.utility.dto.model.CustomerDto;
import com.forhadmethun.reportservice.utility.io.AccountCreationInfo;
import com.forhadmethun.reportservice.utility.io.AccountOperationResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
