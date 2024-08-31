package org.gucardev.genericexample.service;

import org.gucardev.genericexample.dto.AccountDto;
import org.gucardev.genericexample.dto.CardDto;
import org.gucardev.genericexample.entity.Account;

import java.util.List;

public interface AccountService extends GenericService<Account, AccountDto, Long> {
    List<AccountDto> findAllByCustomerId(Long customerId);
}
