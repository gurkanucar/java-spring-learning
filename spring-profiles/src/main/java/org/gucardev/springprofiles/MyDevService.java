package org.gucardev.springprofiles;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("dev")
@Service
@Slf4j
public class MyDevService implements MyService {
    @Override
    public void doSomething() {
        log.info("MyDevService doSomething");
    }
}
