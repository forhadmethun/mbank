package com.forhadmethun.reportservice.db.services.bean;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.reportservice.db.entity.Customer;
import com.forhadmethun.reportservice.db.repository.CustomerRepository;
import com.forhadmethun.reportservice.db.services.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceBean implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceBean(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
