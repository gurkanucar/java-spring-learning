package org.gucardev.mixed.relationship_cases.e_commerce.repo;

import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
