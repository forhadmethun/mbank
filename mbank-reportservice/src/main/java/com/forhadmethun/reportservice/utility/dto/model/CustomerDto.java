package com.forhadmethun.reportservice.utility.dto.model;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private Long customerId;
    private String country;
    private List<String> currencies;
}
