package com.forhadmethun.reportservice.db.services.bean;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.reportservice.db.repository.TransactionRepository;
import com.forhadmethun.reportservice.db.services.BalanceService;
import com.forhadmethun.reportservice.db.services.TransactionService;
import com.forhadmethun.reportservice.utility.io.TransactionCreationInfo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionServiceBean implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final BalanceService balanceService;

    public TransactionServiceBean(TransactionRepository transactionRepository, BalanceService balanceService) {
        this.transactionRepository = transactionRepository;
        this.balanceService = balanceService;
    }

    @Transactional
    @Override
    public void createTransaction(
            TransactionCreationInfo transactionCreationInfo
    ) {
        balanceService.saveBalance(transactionCreationInfo.getBalance());
        transactionRepository.save(transactionCreationInfo.getTransaction());
    }

}
