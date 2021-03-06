package com.forhadmethun.reportservice.db.entity;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.reportservice.utility.dto.model.DirectionOfTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    private Long transactionId;
    private Long accountId;
    private BigDecimal amount;
    private String currency;
    @Enumerated(value = EnumType.STRING)
    private DirectionOfTransaction directionOfTransaction;
    private String description;
}
