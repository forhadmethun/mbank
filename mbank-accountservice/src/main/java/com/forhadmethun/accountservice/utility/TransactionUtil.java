package com.forhadmethun.accountservice.utility;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Balance;
import com.forhadmethun.accountservice.utility.constant.PersistenceConstant;
import com.forhadmethun.accountservice.utility.dto.model.TransactionDto;

import java.math.BigDecimal;
import java.util.*;

import static com.forhadmethun.accountservice.utility.validation.AccountServiceValidation.*;

public class TransactionUtil {
    public static void checkTransactionValidity(
            TransactionDto transaction,
            Optional<Balance> balanceByCurrency
    ){
        notNull.and(checkValidityOfCurrency).test(transaction.getCurrency())
                .setMessage(PersistenceConstant.INVALID_CURRENCY).throwIfInvalid();
        notNullBalance.test(balanceByCurrency.get())
                .setMessage(PersistenceConstant.INVALID_CURRENCY).throwIfInvalid();

        notNullDirectionOfTransaction.and(validDirectionOfTransaction)
                .test(transaction.getDirectionOfTransaction())
                .setMessage(PersistenceConstant.INVALID_DIRECTION).throwIfInvalid();

        notNull.test(transaction.getDescription())
                .setMessage(PersistenceConstant.DESCRIPTION_MISSING).throwIfInvalid();

       notNullAmount.and(invalidAmount).test(transaction.getAmount())
               .setMessage(PersistenceConstant.INVALID_AMOUNT).throwIfInvalid();

       insufficientFunds(transaction.getDirectionOfTransaction(), balanceByCurrency.get().getBalance()).test(transaction.getAmount())
               .setMessage(PersistenceConstant.INSUFFICIENT_FUNDS).throwIfInvalid();
    }

    private BigDecimal getBalanceByCurrency(List<Balance> balances, String currency) {
        var balanceByCurrency = balances.stream()
                .filter(balance -> balance.getCurrency().equals(currency))
                .findFirst();
        return balanceByCurrency.get().getBalance();
    }
}
