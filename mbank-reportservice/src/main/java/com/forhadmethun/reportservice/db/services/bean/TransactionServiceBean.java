package com.forhadmethun.reportservice.db.services.bean;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.reportservice.db.entity.Balance;
import com.forhadmethun.reportservice.db.repository.TransactionRepository;
import com.forhadmethun.reportservice.db.services.BalanceService;
import com.forhadmethun.reportservice.db.services.TransactionService;
import com.forhadmethun.reportservice.utility.dto.mapper.TransactionMapper;
import com.forhadmethun.reportservice.utility.dto.model.DirectionOfTransaction;
import com.forhadmethun.reportservice.utility.dto.model.TransactionDto;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceBean implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final BalanceService balanceService;

    public TransactionServiceBean(TransactionRepository transactionRepository, BalanceService balanceService) {
        this.transactionRepository = transactionRepository;
        this.balanceService = balanceService;
    }

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto, Balance balance) {
        if(transactionDto.getDirectionOfTransaction() ==  DirectionOfTransaction.IN ){
            balance.setBalance(balance.getBalance().add(transactionDto.getAmount()));
        }
        else if(transactionDto.getDirectionOfTransaction() == DirectionOfTransaction.OUT){
            balance.setBalance(balance.getBalance().subtract(transactionDto.getAmount()));
        }
        balanceService.saveBalance(balance);
        var savedTransaction = transactionRepository.save(TransactionMapper.toTransaction(transactionDto));
        var savedTransactionDto = TransactionMapper.toTransactionDto(savedTransaction);
        savedTransactionDto.setBalanceAfterTransaction(balance.getBalance());
        return savedTransactionDto;
    }
}