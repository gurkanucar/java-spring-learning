package org.gucardev.rediscachingexample.repository;

import org.gucardev.rediscachingexample.entity.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepositoryV2 extends CrudRepository<Token, String> {
}
