package com.forhadmethun.reportservice.utility.io;

import com.forhadmethun.reportservice.db.entity.Balance;
import com.forhadmethun.reportservice.db.entity.Transaction;
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
