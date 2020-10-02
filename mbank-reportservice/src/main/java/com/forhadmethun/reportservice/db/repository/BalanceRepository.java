package com.forhadmethun.reportservice.db.repository;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.reportservice.db.entity.Balance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BalanceRepository extends CrudRepository<Balance, Long> {
    List<Balance> findByAccountId(Long accountId);
}
