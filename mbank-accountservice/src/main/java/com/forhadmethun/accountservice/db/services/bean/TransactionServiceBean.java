package com.forhadmethun.accountservice.db.services.bean;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Balance;
import com.forhadmethun.accountservice.db.entity.Transaction;
import com.forhadmethun.accountservice.db.repository.TransactionRepository;
import com.forhadmethun.accountservice.db.services.BalanceService;
import com.forhadmethun.accountservice.db.services.MessageQueueService;
import com.forhadmethun.accountservice.db.services.TransactionService;
import com.forhadmethun.accountservice.utility.TransactionUtil;
import com.forhadmethun.accountservice.utility.dto.mapper.TransactionMapper;
import com.forhadmethun.accountservice.utility.dto.model.DirectionOfTransaction;
import com.forhadmethun.accountservice.utility.dto.model.TransactionDto;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TransactionServiceBean implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final BalanceService balanceService;
    private final MessageQueueService messageQueueService;

    public TransactionServiceBean(
            TransactionRepository transactionRepository,
            BalanceService balanceService,
            MessageQueueService messageQueueService
    ) {
        this.transactionRepository = transactionRepository;
        this.balanceService = balanceService;
        this.messageQueueService = messageQueueService;
    }

    @Transactional
    @Override
    public TransactionDto createTransaction(
            TransactionDto transactionDto
    ) {
        Balance balance = balanceService.findByAccountIdAndCurrency(
                        transactionDto.getAccountId(), transactionDto.getCurrency()
                );

        TransactionUtil.checkTransactionValidity(transactionDto, balance);

        changeBalanceAmountByDirection(transactionDto, balance);

        balanceService.saveBalance(balance);

        Transaction savedTransaction =
                transactionRepository.save(TransactionMapper.toTransaction(transactionDto));

        TransactionDto savedTransactionDto =
                TransactionMapper.toTransactionDto(savedTransaction);

        savedTransactionDto.setBalanceAfterTransaction(balance.getBalance());

        messageQueueService.publishCreateTransaction(savedTransactionDto);

        return savedTransactionDto;
    }

    @Override
    public List<Transaction> findByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    private void changeBalanceAmountByDirection(TransactionDto transactionDto, Balance balance){
        if(transactionDto.getDirectionOfTransaction() ==  DirectionOfTransaction.IN ){
            balance.setBalance(balance.getBalance().add(transactionDto.getAmount()));
        }
        else if(transactionDto.getDirectionOfTransaction() == DirectionOfTransaction.OUT){
            balance.setBalance(balance.getBalance().subtract(transactionDto.getAmount()));
        }
    }
}
