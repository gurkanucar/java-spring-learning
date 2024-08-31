package org.gucardev.genericexample.controller;

import org.gucardev.genericexample.dto.AccountDto;
import org.gucardev.genericexample.dto.CardDto;
import org.gucardev.genericexample.entity.Account;
import org.gucardev.genericexample.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountRestController extends GenericRestController<Account, AccountDto, Long> {

    private final AccountService service;

    @Autowired
    public AccountRestController(AccountService service) {
        super(service);
        this.service = service;
    }

    @Override
    protected List<String> getSearchableFields() {
        return List.of("name");
    }

    @GetMapping("/customer/{customerId}")
    public List<AccountDto> getAccountsByCustomerId(@PathVariable("customerId") Long customerId) {
        return service.findAllByCustomerId(customerId);
    }
}
