package com.forhadmethun.reportservice.db.services;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.reportservice.db.entity.Account;
import com.forhadmethun.reportservice.utility.exception.PersistenceException;

public interface AccountService {
    Account createAccount(Account account);
    Account findByAccountId(Long accountId) throws PersistenceException;
}
