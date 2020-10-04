package com.forhadmethun.accountservice.utility.io.mq;

import com.forhadmethun.accountservice.utility.dto.model.AccountDto;
import com.forhadmethun.accountservice.utility.dto.model.CustomerDto;
import com.forhadmethun.accountservice.utility.dto.model.mq.BalanceMQDto;
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
    private CustomerDto customer;
    private AccountDto account;
    private List<BalanceMQDto> balances;
}
