package org.gucardev.entityrelationshipexamples.repository;

import org.gucardev.entityrelationshipexamples.model.LookupCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LookupCategoryRepository extends JpaRepository<LookupCategory, Long> {
}
