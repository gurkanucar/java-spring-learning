package org.gucardev.entityrelationships.repository;

import org.gucardev.entityrelationships.model.LookupCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LookupCategoryRepository extends JpaRepository<LookupCategory, Long> {
}
