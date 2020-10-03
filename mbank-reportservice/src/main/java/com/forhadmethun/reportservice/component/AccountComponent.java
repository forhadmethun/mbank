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
import com.forhadmethun.reportservice.utility.io.AccountCreationInfo;
import com.forhadmethun.reportservice.utility.io.AccountOperationResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountComponent {
    private final CustomerService customerService;

    public AccountComponent(
            CustomerService customerService
    ) {
        this.customerService = customerService;
    }

    @RabbitListener(queues = RabbitMQConfiguration.QUEUE_ACCOUNT)
    public void createAccount(
            AccountCreationInfo accountCreationInfo
    ) {
        customerService.createCustomer(accountCreationInfo);
    }

}
