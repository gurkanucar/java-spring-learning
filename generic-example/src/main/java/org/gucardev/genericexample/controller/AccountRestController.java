package org.gucardev.genericexample.controller;

import org.gucardev.genericexample.dto.AccountDto;
import org.gucardev.genericexample.entity.Account;
import org.gucardev.genericexample.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountRestController extends GenericRestController<Account, AccountDto, Long> {

    @Autowired
    public AccountRestController(AccountService service) {
        super(service);
    }

    @Override
    protected List<String> getSearchableFields() {
        return List.of("name");
    }

}
