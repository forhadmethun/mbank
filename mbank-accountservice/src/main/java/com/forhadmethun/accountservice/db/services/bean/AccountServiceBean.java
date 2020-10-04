package com.forhadmethun.accountservice.db.services.bean;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Account;
import com.forhadmethun.accountservice.db.entity.Balance;
import com.forhadmethun.accountservice.db.repository.AccountQueryRepository;
import com.forhadmethun.accountservice.db.repository.AccountCommandRepository;
import com.forhadmethun.accountservice.db.repository.BalanceQueryRepository;
import com.forhadmethun.accountservice.db.services.AccountService;
import com.forhadmethun.accountservice.db.services.BalanceService;
import com.forhadmethun.accountservice.utility.constant.PersistenceConstant;
import com.forhadmethun.accountservice.utility.dto.mapper.BalanceMapper;
import com.forhadmethun.accountservice.utility.dto.model.AccountDto;
import com.forhadmethun.accountservice.utility.exception.PersistenceException;
import com.forhadmethun.accountservice.utility.io.AccountOperationResponse;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class AccountServiceBean implements AccountService {
    private final AccountCommandRepository accountCommandRepository;
    private final AccountQueryRepository accountQueryRepository;
    private final BalanceService balanceService;

    @Override
    public Account createAccount(Account account) {
        return accountCommandRepository.save(account);
    }

    @Override
    public AccountOperationResponse findByAccountId(Long accountId) throws PersistenceException {
        AccountDto account =  accountQueryRepository.findByAccountId(accountId);

        if (account == null)
            throw new PersistenceException(PersistenceConstant.ACCOUNT_NOT_FOUND);

        List<Balance> balances = balanceService.findByAccountId(accountId);

        AccountOperationResponse accountOperationResponse =
                AccountOperationResponse.createAccountCreationResponse(
                        account.getAccountId(),
                        account.getCustomerId(),
                        BalanceMapper.toBalanceDtoList(balances)
                );

        return accountOperationResponse;
    }
}
