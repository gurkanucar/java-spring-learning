package org.gucardev.entityrelationshipexamples.repository;

import org.gucardev.entityrelationshipexamples.model.LookupValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LookupValueRepository extends JpaRepository<LookupValue, Long> {
    List<LookupValue> findByCategoryId(Long categoryId);

    LookupValue findByKey(String engineer);

    Page<LookupValue> findAll(Specification<LookupValue> spec, Pageable pageable);
}
