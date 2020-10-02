package com.forhadmethun.reportservice.component;

/**
 * @author Md Forhad Hossain
 * @since 03/10/20
 */

import com.forhadmethun.reportservice.config.RabbitMQConfiguration;
import com.forhadmethun.reportservice.db.services.AccountService;
import com.forhadmethun.reportservice.db.services.BalanceService;
import com.forhadmethun.reportservice.db.services.TransactionService;
import com.forhadmethun.reportservice.utility.dto.model.TransactionDto;
import com.forhadmethun.reportservice.utility.exception.PersistenceException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionComponent {
    private final TransactionService transactionService;
    private final AccountService accountService;
    private final BalanceService balanceService;

    public TransactionComponent(TransactionService transactionService, AccountService accountService, BalanceService balanceService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
        this.balanceService = balanceService;
    }

    @RabbitListener(queues = RabbitMQConfiguration.QUEUE_TRANSACTION)
    public void createTransaction(
            TransactionDto transactionDto
    ) throws PersistenceException {
        System.out.println(transactionDto.toString());
        var account =  accountService.findByAccountId(transactionDto.getAccountId());
        var balances = balanceService.findByAccountId(account.getAccountId());
        var balanceInTransactionCurrency = balances.stream()
                .filter(balance -> balance.getCurrency().equals(transactionDto.getCurrency()))
                .findFirst();
        transactionService.createTransaction(
                transactionDto,
                balanceInTransactionCurrency.get());
    }

}
