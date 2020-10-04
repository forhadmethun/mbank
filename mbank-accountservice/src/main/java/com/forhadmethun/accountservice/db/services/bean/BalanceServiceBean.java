package com.forhadmethun.accountservice.db.services.bean;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Balance;
import com.forhadmethun.accountservice.db.repository.BalanceCommandRepository;
import com.forhadmethun.accountservice.db.repository.BalanceQueryRepository;
import com.forhadmethun.accountservice.db.services.BalanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class BalanceServiceBean implements BalanceService {
    private final BalanceCommandRepository balanceCommandRepository;
    private final BalanceQueryRepository balanceQueryRepository;

    @Override
    public Balance saveBalance(Balance balance) {
        return balanceCommandRepository.save(balance);
    }

    @Override
    public Balance findByAccountIdAndCurrency(Long accountId, String currency) {
        Balance balance =
                balanceQueryRepository.findByAccountIdAndCurrency(accountId, currency);

        return balance;
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
