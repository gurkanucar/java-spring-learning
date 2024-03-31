package com.gucardev.springlearning.relationship_cases.e_commerce.repo;

import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {
}
