package com.forhadmethun.reportservice.db.repository;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.reportservice.db.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
