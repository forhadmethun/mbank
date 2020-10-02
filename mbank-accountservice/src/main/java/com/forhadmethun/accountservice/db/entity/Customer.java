package com.forhadmethun.accountservice.db.entity;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    private Long customerId;
    private String country;
}
