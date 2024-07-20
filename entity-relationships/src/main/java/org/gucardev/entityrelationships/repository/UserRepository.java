package org.gucardev.entityrelationships.repository;

import org.gucardev.entityrelationships.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

