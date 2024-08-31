package org.gucardev.genericexample.repository;

import org.gucardev.genericexample.entity.Card;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CardRepository extends GenericRepository<Card, Long> {
    List<Card> findAllByAccountId(Long customerId);
}


