package org.gucardev.mixed.relationship_cases.feign_transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private MyService myService;

    @GetMapping("/do-something/{data}")
    public String doSomethingAndCallApi(@PathVariable String data) {
        myService.doSomethingAndCallApi(data);
        return "Operation completed";
    }

}
