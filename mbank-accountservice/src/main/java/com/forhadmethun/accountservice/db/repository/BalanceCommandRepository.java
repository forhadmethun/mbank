package com.forhadmethun.accountservice.db.repository;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Balance;
import org.springframework.data.repository.CrudRepository;

public interface BalanceCommandRepository extends CrudRepository<Balance, Long> {
}
