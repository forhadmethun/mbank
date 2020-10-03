package com.forhadmethun.reportservice.component;

/**
 * @author Md Forhad Hossain
 * @since 03/10/20
 */

import com.forhadmethun.reportservice.config.RabbitMQConfiguration;
import com.forhadmethun.reportservice.db.services.TransactionService;
import com.forhadmethun.reportservice.utility.dto.model.TransactionDto;
import com.forhadmethun.reportservice.utility.io.TransactionCreationInfo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionComponent {
    private final TransactionService transactionService;

    public TransactionComponent(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RabbitListener(queues = RabbitMQConfiguration.QUEUE_TRANSACTION)
    public void createTransaction(
            TransactionCreationInfo transactionCreationInfo
    ){
        transactionService.createTransaction(transactionCreationInfo);
    }
}
