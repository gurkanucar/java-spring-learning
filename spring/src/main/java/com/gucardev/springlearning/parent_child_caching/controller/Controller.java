package com.gucardev.springlearning.parent_child_caching.controller;

import com.gucardev.springlearning.parent_child_caching.dto.CustomerDto;
import com.gucardev.springlearning.parent_child_caching.service.CustomerServicee;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-customer")
public class Controller {

    private final CustomerServicee customerServicee;

    @GetMapping("/customers")
    public List<CustomerDto> getCustomers() {
        return customerServicee.getAllCustomers();
    }

}
