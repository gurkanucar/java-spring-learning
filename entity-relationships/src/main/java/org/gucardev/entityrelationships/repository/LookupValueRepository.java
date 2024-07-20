package org.gucardev.entityrelationships.repository;

import org.gucardev.entityrelationships.model.LookupValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LookupValueRepository extends JpaRepository<LookupValue, Long> {
}
