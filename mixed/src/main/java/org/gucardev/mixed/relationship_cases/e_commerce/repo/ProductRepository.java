package org.gucardev.mixed.relationship_cases.e_commerce.repo;

import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategories_Id(Long id);

}

