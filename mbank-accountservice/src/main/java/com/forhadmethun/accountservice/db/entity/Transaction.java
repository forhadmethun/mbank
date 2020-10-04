package com.forhadmethun.accountservice.db.entity;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.utility.dto.model.DirectionOfTransaction;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    private static final String SEQ_TRANSACTION = "seq_transaction_id";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TRANSACTION)
    @SequenceGenerator(name= SEQ_TRANSACTION, sequenceName = SEQ_TRANSACTION, allocationSize = 1)
    private Long transactionId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency")
    private String currency;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "direction_of_transaction")
    private DirectionOfTransaction directionOfTransaction;

    @Column(name = "description")
    private String description;
}
