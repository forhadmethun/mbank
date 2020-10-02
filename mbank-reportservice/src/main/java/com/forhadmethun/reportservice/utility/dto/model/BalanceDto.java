package com.forhadmethun.reportservice.utility.dto.model;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BalanceDto {
    private String currency;
    private BigDecimal balance;
}
