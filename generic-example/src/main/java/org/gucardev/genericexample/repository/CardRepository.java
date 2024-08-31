package org.gucardev.genericexample.repository;

import org.gucardev.genericexample.entity.Card;
import org.springframework.stereotype.Repository;


@Repository
public interface CardRepository extends GenericRepository<Card, Long> {
}


