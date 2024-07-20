package org.gucardev.entityrelationships.repository;

import org.gucardev.entityrelationships.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
