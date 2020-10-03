package com.forhadmethun.accountservice.db.services.bean;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Balance;
import com.forhadmethun.accountservice.db.repository.BalanceCommandRepository;
import com.forhadmethun.accountservice.db.repository.BalanceQueryRepository;
import com.forhadmethun.accountservice.db.services.BalanceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BalanceServiceBean implements BalanceService {
    private final BalanceCommandRepository balanceCommandRepository;
    private final BalanceQueryRepository balanceQueryRepository;

    public BalanceServiceBean(BalanceCommandRepository balanceCommandRepository,
                              BalanceQueryRepository balanceQueryRepository) {
        this.balanceCommandRepository = balanceCommandRepository;
        this.balanceQueryRepository = balanceQueryRepository;
    }

    @Override
    public Balance saveBalance(Balance balance) {
        return balanceCommandRepository.save(balance);
    }

    @Override
    public Balance findByAccountIdAndCurrency(Long accountId, String currency) {
        return balanceQueryRepository.findByAccountIdAndCurrency(accountId, currency);
    }

    @Override
    public List<Balance> saveBalance(List<Balance> balances) {
        return StreamSupport.stream(
                balanceCommandRepository.saveAll(balances).spliterator(),
                false
        ).collect(Collectors.toList());
    }

    @Override
    public List<Balance> findByAccountId(Long accountId) {
        return balanceQueryRepository.findByAccountId(accountId);
    }
}
