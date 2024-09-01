package org.gucardev.springprofiles;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringProfilesApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringProfilesApplication.class, args);
    }

    @Value("${my.variable}")
    private String myVariable;

    @Autowired
    private MyClass myClass;

    @Autowired
    private MyService myService;

    @Autowired
    private MyCustomService myCustomService;

    @Override
    public void run(String... args) throws Exception {
        log.info("env variable | my.variable={}", myVariable);
        log.info("class | {}", myClass.toString());
        myService.doSomething();
        myCustomService.doSomething();
        myCustomService.doSomethingElse();
    }
}
