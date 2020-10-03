package com.forhadmethun.accountservice.db.services.bean;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Account;
import com.forhadmethun.accountservice.db.repository.AccountRepository;
import com.forhadmethun.accountservice.db.services.AccountService;
import com.forhadmethun.accountservice.db.services.BalanceService;
import com.forhadmethun.accountservice.utility.constant.PersistenceConstant;
import com.forhadmethun.accountservice.utility.dto.mapper.BalanceMapper;
import com.forhadmethun.accountservice.utility.exception.PersistenceException;
import com.forhadmethun.accountservice.utility.io.AccountOperationResponse;

import org.springframework.stereotype.Service;

@Service
public class AccountServiceBean implements AccountService {
    private final AccountRepository accountRepository;
    private final BalanceService balanceService;

    public AccountServiceBean(AccountRepository accountRepository, BalanceService balanceService) {
        this.accountRepository = accountRepository;
        this.balanceService = balanceService;
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public AccountOperationResponse findByAccountId(Long accountId) throws PersistenceException {
        Account account =  accountRepository.findByAccountId(accountId);

        if (account == null)
            throw new PersistenceException(PersistenceConstant.ACCOUNT_NOT_FOUND);

        var balances = balanceService.findByAccountId(accountId);

        AccountOperationResponse accountOperationResponse =
                AccountOperationResponse.createAccountCreationResponse(
                        account.getAccountId(),
                        account.getCustomerId(),
                        BalanceMapper.toBalanceDtoList(balances)
                );

        return accountOperationResponse;
    }
}
