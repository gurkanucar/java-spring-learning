package org.gucardev.genericexample.repository;

import org.gucardev.genericexample.entity.Customer;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends GenericRepository<Customer, Long> {
}


