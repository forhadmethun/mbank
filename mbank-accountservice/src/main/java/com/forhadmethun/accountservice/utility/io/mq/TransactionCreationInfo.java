package com.forhadmethun.accountservice.utility.io.mq;

import com.forhadmethun.accountservice.utility.dto.model.TransactionDto;
import com.forhadmethun.accountservice.utility.dto.model.mq.BalanceMQDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionCreationInfo {
    private TransactionDto transaction;
    private BalanceMQDto balance;
}
