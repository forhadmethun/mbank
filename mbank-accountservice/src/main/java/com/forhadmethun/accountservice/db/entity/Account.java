package com.forhadmethun.accountservice.db.entity;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.List;

@Entity(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    private static final String SEQ_ACCOUNT = "seq_account_id";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_ACCOUNT)
    @SequenceGenerator(name= SEQ_ACCOUNT, sequenceName = SEQ_ACCOUNT, allocationSize = 1)
    @Column(name = "account_id")
    private Long accountId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    List<Balance> balances;
}
