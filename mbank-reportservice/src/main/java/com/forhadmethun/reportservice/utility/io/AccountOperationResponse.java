package com.forhadmethun.reportservice.utility.io;

import com.forhadmethun.reportservice.utility.dto.model.BalanceDto;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
