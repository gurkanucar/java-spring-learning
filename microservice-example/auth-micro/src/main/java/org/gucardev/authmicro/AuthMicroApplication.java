package org.gucardev.authmicro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AuthMicroApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthMicroApplication.class, args);
    }

}
