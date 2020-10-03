package com.forhadmethun.accountservice.utility.io;

import com.forhadmethun.accountservice.db.entity.Balance;

import com.forhadmethun.accountservice.db.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionCreationInfo {
    private Transaction transaction;
    private Balance balance;
}
