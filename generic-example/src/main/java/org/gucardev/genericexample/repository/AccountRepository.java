package org.gucardev.genericexample.repository;

import org.gucardev.genericexample.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface AccountRepository extends GenericRepository<Account, Long> {
    List<Account> findAllByCustomerId(Long customerId);
}


