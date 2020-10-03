package com.forhadmethun.accountservice.db.services.bean;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Balance;
import com.forhadmethun.accountservice.db.repository.BalanceRepository;
import com.forhadmethun.accountservice.db.services.BalanceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BalanceServiceBean implements BalanceService {
    private final BalanceRepository balanceRepository;

    public BalanceServiceBean(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public Balance saveBalance(Balance balance) {
        return balanceRepository.save(balance);
    }

    @Override
    public Balance findByAccountIdAndCurrency(Long accountId, String currency) {
        return balanceRepository.findByAccountIdAndCurrency(accountId, currency);
    }

    @Override
    public List<Balance> saveBalance(List<Balance> balances) {
        return StreamSupport.stream(
                balanceRepository.saveAll(balances).spliterator(),
                false
        ).collect(Collectors.toList());
    }

    @Override
    public List<Balance> findByAccountId(Long accountId) {
        return balanceRepository.findByAccountId(accountId);
    }
}
