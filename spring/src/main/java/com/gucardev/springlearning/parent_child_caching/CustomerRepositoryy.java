package com.gucardev.springlearning.parent_child_caching;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepositoryy extends JpaRepository<Customerr, Long> {
}
