package com.forhadmethun.reportservice.utility.constant;

/**
 * @author Md Forhad Hossain
 * @since 02/10/20
 */

public class PersistenceConstant {
    private PersistenceConstant() {
    }

    public static final String ACCOUNT_ALREADY_EXISTS = "Account with provided account id already exist";
    public static final String ACCOUNT_NOT_FOUND = "Account does not exists with provided account id.";

    public static final String INVALID_CURRENCY = "Invalid currency";
    public static final String INVALID_DIRECTION = "Invalid direction";
    public static final String INVALID_AMOUNT = "Invalid amount";
    public static final String INSUFFICIENT_FUNDS = "Insufficient amount";
    public static final String DESCRIPTION_MISSING = "Description Missing";
}
