package com.forhadmethun.reportservice.db.entity;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    private Long accountId;
    private Long customerId;
}
