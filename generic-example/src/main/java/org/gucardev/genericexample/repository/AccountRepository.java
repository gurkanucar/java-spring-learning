package org.gucardev.genericexample.repository;

import org.gucardev.genericexample.entity.Account;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends GenericRepository<Account, Long> {
}


