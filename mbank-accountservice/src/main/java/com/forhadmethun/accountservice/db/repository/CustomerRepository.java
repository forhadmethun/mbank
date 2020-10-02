package com.forhadmethun.accountservice.db.repository;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
