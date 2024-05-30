package com.gucardev.springlearning.relationship_cases.lookup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LookupRepository extends JpaRepository<Lookup, Long> {
}
