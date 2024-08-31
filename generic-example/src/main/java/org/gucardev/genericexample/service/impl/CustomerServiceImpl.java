package org.gucardev.genericexample.service.impl;

import org.gucardev.genericexample.dto.CustomerDto;
import org.gucardev.genericexample.entity.Customer;
import org.gucardev.genericexample.mapper.CustomerMapper;
import org.gucardev.genericexample.repository.CustomerRepository;
import org.gucardev.genericexample.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer, CustomerDto, Long> implements CustomerService {
    public CustomerServiceImpl(CustomerRepository repository, CustomerMapper mapper) {
        super(repository, mapper);
    }
}

