package org.gucardev.genericexample.controller;

import org.gucardev.genericexample.dto.CustomerDto;
import org.gucardev.genericexample.entity.Customer;
import org.gucardev.genericexample.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController extends GenericRestController<Customer, CustomerDto, Long> {

    @Autowired
    public CustomerRestController(CustomerService service) {
        super(service);
    }

    @Override
    protected List<String> getSearchableFields() {
        return List.of("name", "email");
    }

}

