package org.gucardev.springprofiles;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyCustomService {

    @RunOnProfile({"dev", "test"})
    public void doSomething() {
        log.info("MyCustomService | I'm doing something on dev or test");
    }

    @RunOnProfile({"!dev"})
    public void doSomethingElse() {
        log.info("MyCustomService | I'm doing something on profile other than dev");
    }

}
