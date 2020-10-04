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

@Entity(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    private static final String SEQ_CUSTOMER = "seq_customer_id";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_CUSTOMER)
    @SequenceGenerator(name=SEQ_CUSTOMER, sequenceName = SEQ_CUSTOMER, allocationSize = 1)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Account> accounts;
}
