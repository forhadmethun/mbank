package com.forhadmethun.accountservice.utility;

import com.forhadmethun.accountservice.utility.constant.PersistenceConstant;
import com.forhadmethun.accountservice.utility.exception.RequestException;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */


public class CurrencyUtil{
    private static List<String> currencyCodes = Arrays.asList(
            "EUR", "SEK", "BP", "USD"
    );
    private static Set<String> currencyLookupSet = new HashSet<String>(currencyCodes);
    public static boolean isValidCurrency(String currencyCode){
        return CurrencyUtil.currencyLookupSet.contains(currencyCode);
    }
    public static void checkCurrencyValidity(List<String> currencies)
            throws RequestException {
        List<String> invalidCurrencies = new ArrayList<>();
        for(String currencyCode : currencies){
            if(!isValidCurrency(currencyCode)){
                invalidCurrencies.add(currencyCode);
            }
        }
        if(!invalidCurrencies.isEmpty()){
            throw new RequestException(PersistenceConstant.INVALID_CURRENCY + " : "
                    + Arrays.toString(invalidCurrencies.toArray()));
        }
    }
}
