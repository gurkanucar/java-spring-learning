package com.gucardev.springlearning.relationship_cases.e_commerce.repo;

import com.gucardev.springlearning.relationship_cases.e_commerce.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

