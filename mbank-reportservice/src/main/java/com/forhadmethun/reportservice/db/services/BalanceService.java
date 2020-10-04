package com.forhadmethun.reportservice.db.services;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.reportservice.db.entity.Balance;

import java.util.List;

public interface BalanceService {
    Balance saveBalance(Balance balance);

    List<Balance> saveBalance(List<Balance> balance);

    List<Balance> findByAccountId(Long accountId);
}
