package com.forhadmethun.reportservice.component;

/**
 * @author Md Forhad Hossain
 * @since 03/10/20
 */

import com.forhadmethun.reportservice.config.RabbitMQConfiguration;
import com.forhadmethun.reportservice.db.services.CustomerService;
import com.forhadmethun.reportservice.utility.io.AccountCreationInfo;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountComponent {
    private final CustomerService customerService;

    @RabbitListener(queues = RabbitMQConfiguration.QUEUE_ACCOUNT)
    public void createAccount(
            AccountCreationInfo accountCreationInfo
    ) {
        customerService.createCustomer(accountCreationInfo);
    }

}
