package com.gucardev.springlearning.parent_child_caching;

import com.gucardev.springlearning.parent_child_caching.dao.repo.CustomerRepositoryy;
import com.gucardev.springlearning.parent_child_caching.dao.entity.Customerr;
import com.gucardev.springlearning.parent_child_caching.dao.repo.UserRepositoryy;
import com.gucardev.springlearning.parent_child_caching.dao.entity.Userr;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitialDataPopulate implements CommandLineRunner {

    @Autowired
    private UserRepositoryy userRepositoryy;

    @Autowired
    private CustomerRepositoryy customerRepositoryy;

    @Override
    public void run(String... args) throws Exception {
        var user1 = userRepositoryy.save(new Userr("gurkan"));
        var user2 = userRepositoryy.save(new Userr("sezai"));

        var cust1 = customerRepositoryy.save(new Customerr("kartal", user1.getId()));
        var cust2 = customerRepositoryy.save(new Customerr("sezai", user1.getId()));
        var cust3 = customerRepositoryy.save(new Customerr("ali", user2.getId()));
        var cust4 = customerRepositoryy.save(new Customerr("metin", user1.getId()));
    }
}
