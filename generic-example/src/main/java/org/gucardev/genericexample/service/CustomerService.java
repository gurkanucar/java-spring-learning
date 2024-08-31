package org.gucardev.genericexample.service;

import org.gucardev.genericexample.dto.CustomerDto;
import org.gucardev.genericexample.entity.Customer;

public interface CustomerService extends GenericService<Customer, CustomerDto, Long> {
}

