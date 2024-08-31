package org.gucardev.genericexample.service.impl;

import org.gucardev.genericexample.dto.AccountDto;
import org.gucardev.genericexample.entity.Account;
import org.gucardev.genericexample.mapper.AccountMapper;
import org.gucardev.genericexample.repository.AccountRepository;
import org.gucardev.genericexample.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountServiceImpl extends GenericServiceImpl<Account, AccountDto, Long> implements AccountService {

    private final AccountRepository repository;
    private final AccountMapper mapper;

    public AccountServiceImpl(AccountRepository repository, AccountMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<AccountDto> findAllByCustomerId(Long customerId) {
        return repository.findAllByCustomerId(customerId).stream().map(mapper::toDtoWithCards).toList();
    }


}