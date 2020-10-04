package com.forhadmethun.accountservice.db.services.bean;

/**
 * @author Md Forhad Hossain
 * @since 04/10/20
 */

import com.forhadmethun.accountservice.db.services.MessageQueueService;
import com.forhadmethun.accountservice.utility.io.mq.AccountCreationInfo;

import com.forhadmethun.accountservice.utility.io.mq.TransactionCreationInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MessageQueueBean implements MessageQueueService {
    @Value("${topic.exchange}")
    private String EXCHANGE;

    @Value("${exchange.queue.account.routing.key}")
    public String ROUTING_KEY_ACCOUNT;

    @Value("${exchange.queue.transaction.routing.key}")
    public String ROUTING_KEY_TRANSACTION;

    private final RabbitTemplate rabbitTemplate;

    public MessageQueueBean(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Async
    @Override
    public void publishCreateAccount(AccountCreationInfo accountCreationInfo) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_ACCOUNT, accountCreationInfo);
    }

    @Async
    @Override
    public void publishCreateTransaction(TransactionCreationInfo transactionCreationInfo) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_TRANSACTION, transactionCreationInfo);
    }
}
