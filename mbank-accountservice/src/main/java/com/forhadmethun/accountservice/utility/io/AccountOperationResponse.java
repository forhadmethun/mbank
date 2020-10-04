package com.forhadmethun.accountservice.utility.io;

import com.forhadmethun.accountservice.utility.dto.model.BalanceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountOperationResponse {
    private Long accountId;
    private Long customerId;
    private List<BalanceDto> balances;

    public static AccountOperationResponse createAccountCreationResponse(
            Long accountId,
            Long customerId,
            List<BalanceDto> balances
    ){
        return AccountOperationResponse.builder()
                .accountId(accountId)
                .customerId(customerId)
                .balances(balances)
                .build();
    }
}
