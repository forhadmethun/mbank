package com.forhadmethun.reportservice.db.repository;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.reportservice.db.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByAccountId(Long accountId);
}
