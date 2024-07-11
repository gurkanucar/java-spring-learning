package com.gucardev.springlearning.parent_child_caching;

import com.gucardev.springlearning.relationship_cases.e_commerce.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServicee {

    private final CustomerRepositoryy customerRepository;
    private final CustomerMapperr customerMapper;

    public List<CustomerDto> getAllCustomers() {
        List<Customerr> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }
}
