package org.gucardev.genericexample.service.impl;

import org.gucardev.genericexample.dto.AccountDto;
import org.gucardev.genericexample.entity.Account;
import org.gucardev.genericexample.mapper.AccountMapper;
import org.gucardev.genericexample.repository.AccountRepository;
import org.gucardev.genericexample.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends GenericServiceImpl<Account, AccountDto, Long> implements AccountService {
    public AccountServiceImpl(AccountRepository repository, AccountMapper mapper) {
        super(repository, mapper);
    }
}
