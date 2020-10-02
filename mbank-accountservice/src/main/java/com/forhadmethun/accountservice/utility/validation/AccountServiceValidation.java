package com.forhadmethun.accountservice.utility.validation;

/**
 * @author Md Forhad Hossain
 * @since 02/10/20
 */

import com.forhadmethun.accountservice.db.entity.Balance;
import com.forhadmethun.accountservice.utility.CurrencyUtil;
import com.forhadmethun.accountservice.utility.constant.PersistenceConstant;
import com.forhadmethun.accountservice.utility.dto.model.DirectionOfTransaction;

import java.math.BigDecimal;

import static java.lang.String.format;

public class AccountServiceValidation {
    public static Validation<String> notNull = ValidationImpl.from((s) -> s != null, "must not be null.");
    public static Validation<BigDecimal> notNullAmount = ValidationImpl.from((s) -> s != null, "must not be null.");
    public static Validation<Balance> notNullBalance = ValidationImpl.from((s) -> s != null, "must not be null.");

    public static Validation<DirectionOfTransaction> notNullDirectionOfTransaction =
            ValidationImpl.from((s) -> s != null, "must not be null.");

    public static Validation<DirectionOfTransaction> validDirectionOfTransaction =
            ValidationImpl.from((s) -> DirectionOfTransaction.contains(s.name()), PersistenceConstant.INVALID_DIRECTION);
    public static Validation<String> checkValidityOfCurrency =
            ValidationImpl.from((s) -> CurrencyUtil.isValidCurrency(s), PersistenceConstant.INVALID_CURRENCY);

    public static Validation<BigDecimal> invalidAmount =
            ValidationImpl.from((s) -> s.compareTo(BigDecimal.ZERO) != -1, PersistenceConstant.INVALID_AMOUNT);

    public static Validation<BigDecimal> insufficientFunds(
            DirectionOfTransaction directionOfTransaction,
            BigDecimal accountBalance
    ) {
        return ValidationImpl.from((transactionAmount) -> !(directionOfTransaction == DirectionOfTransaction.OUT &&
                accountBalance.compareTo(transactionAmount) == -1), format(PersistenceConstant.INSUFFICIENT_FUNDS));
    }
}
