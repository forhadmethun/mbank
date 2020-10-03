package com.forhadmethun.reportservice.utility.io;

import com.forhadmethun.reportservice.db.entity.Account;
import com.forhadmethun.reportservice.db.entity.Balance;
import com.forhadmethun.reportservice.db.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountCreationInfo {
    private Customer customer;
    private Account account;
    private List<Balance> balances;
}
