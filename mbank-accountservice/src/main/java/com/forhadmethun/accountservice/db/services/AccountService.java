package com.forhadmethun.accountservice.db.services;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Account;
import com.forhadmethun.accountservice.utility.exception.PersistenceException;

public interface AccountService {
    Account createAccount(Account account);
    Account findByAccountId(Long accountId) throws PersistenceException;
}
