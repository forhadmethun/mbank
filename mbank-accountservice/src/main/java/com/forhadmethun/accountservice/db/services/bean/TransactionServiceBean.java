package com.forhadmethun.accountservice.db.services.bean;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Balance;
import com.forhadmethun.accountservice.db.entity.Transaction;
import com.forhadmethun.accountservice.db.repository.AccountQueryRepository;
import com.forhadmethun.accountservice.db.repository.TransactionQueryRepository;
import com.forhadmethun.accountservice.db.repository.TransactionRepository;
import com.forhadmethun.accountservice.db.services.BalanceService;
import com.forhadmethun.accountservice.db.services.MessageQueueService;
import com.forhadmethun.accountservice.db.services.TransactionService;
import com.forhadmethun.accountservice.utility.TransactionUtil;
import com.forhadmethun.accountservice.utility.constant.PersistenceConstant;
import com.forhadmethun.accountservice.utility.dto.mapper.AccountMapper;
import com.forhadmethun.accountservice.utility.dto.mapper.BalanceMapper;
import com.forhadmethun.accountservice.utility.dto.mapper.TransactionMapper;
import com.forhadmethun.accountservice.utility.dto.model.AccountDto;
import com.forhadmethun.accountservice.utility.dto.model.DirectionOfTransaction;
import com.forhadmethun.accountservice.utility.dto.model.TransactionDto;
import com.forhadmethun.accountservice.utility.exception.PersistenceException;
import com.forhadmethun.accountservice.utility.io.mq.TransactionCreationInfo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceBean implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionQueryRepository transactionQueryRepository;
    private final BalanceService balanceService;
    private final MessageQueueService messageQueueService;
    private final AccountQueryRepository accountQueryRepository;

    @Transactional
    @Override
    public TransactionDto createTransaction(
            TransactionDto transactionDto
    ) {
        Balance balance = balanceService.findByAccountIdAndCurrency(
                        transactionDto.getAccountId(), transactionDto.getCurrency()
                );

        TransactionUtil.checkTransactionValidity(transactionDto, balance);

        balance.setAccount(
                AccountMapper.toAccount(
                        accountQueryRepository.findByAccountId(transactionDto.getAccountId()))
        );

        changeBalanceAmountByDirection(transactionDto, balance);
        Balance savedBalance = balanceService.saveBalance(balance);

        Transaction savedTransaction =
                transactionRepository.save(TransactionMapper.toTransaction(transactionDto, balance));

        TransactionDto savedTransactionDto =
                TransactionMapper.toTransactionDto(savedTransaction);

        savedTransactionDto.setBalanceAfterTransaction(balance.getBalance());

        messageQueueService.publishCreateTransaction(
                TransactionCreationInfo.builder()
                    .transaction(TransactionMapper.toTransactionDto(savedTransaction))
                    .balance(BalanceMapper.toBalanceMQDto(savedBalance, transactionDto.getAccountId()))
                    .build()
        );

        return savedTransactionDto;
    }

    @Override
    public List<TransactionDto> findByAccountId(Long accountId) throws PersistenceException {
        AccountDto account =  accountQueryRepository.findByAccountId(accountId);

        if (account == null)
            throw new PersistenceException(PersistenceConstant.ACCOUNT_NOT_FOUND);

        return transactionQueryRepository.findByAccountId(accountId);
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
