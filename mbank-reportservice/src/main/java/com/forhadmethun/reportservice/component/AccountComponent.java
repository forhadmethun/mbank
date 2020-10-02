package com.forhadmethun.reportservice.component;

/**
 * @author Md Forhad Hossain
 * @since 03/10/20
 */

import com.forhadmethun.reportservice.config.RabbitMQConfiguration;
import com.forhadmethun.reportservice.db.services.AccountService;
import com.forhadmethun.reportservice.db.services.BalanceService;
import com.forhadmethun.reportservice.db.services.CustomerService;
import com.forhadmethun.reportservice.utility.dto.mapper.AccountMapper;
import com.forhadmethun.reportservice.utility.dto.mapper.BalanceMapper;
import com.forhadmethun.reportservice.utility.dto.mapper.CustomerMapper;
import com.forhadmethun.reportservice.utility.dto.model.CustomerDto;
import com.forhadmethun.reportservice.utility.io.AccountOperationResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountComponent {
    private final CustomerService customerService;
    private final AccountService accountService;
    private final BalanceService balanceService;
    public AccountComponent(
            CustomerService customerService,
            AccountService accountService,
            BalanceService balanceService,
            RabbitTemplate rabbitTemplate) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.balanceService = balanceService;
    }

    @RabbitListener(queues = RabbitMQConfiguration.QUEUE_ACCOUNT)
    public void createAccount(
            AccountOperationResponse accountOperationResponse
    ){
        System.out.println(accountOperationResponse.toString());
        CustomerDto customerDto = CustomerDto.builder()
                .customerId(accountOperationResponse.getCustomerId())
                .build();
        customerService.createCustomer(CustomerMapper.toCustomer(customerDto));
        var account = AccountMapper.toAccountFromCustomerDto(customerDto);
        account.setAccountId(accountOperationResponse.getAccountId());
        accountService.createAccount(account);
        balanceService.saveBalance(
                BalanceMapper.toBalanceList(accountOperationResponse.getBalances())
        );
    }

}
