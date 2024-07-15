package org.gucardev.mixed.relationship_cases.feign_transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyEntityRepository extends JpaRepository<MyEntity, Long> {
}
