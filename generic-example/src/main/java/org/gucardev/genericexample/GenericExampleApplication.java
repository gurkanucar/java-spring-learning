package org.gucardev.genericexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GenericExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenericExampleApplication.class, args);
    }

}
