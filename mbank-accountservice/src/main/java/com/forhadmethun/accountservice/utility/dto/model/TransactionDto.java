package com.forhadmethun.accountservice.utility.dto.model;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {
    private Long transactionId;
    private Long accountId;
    private BigDecimal amount;
    private String currency;
    private DirectionOfTransaction directionOfTransaction;
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal balanceAfterTransaction;
}
