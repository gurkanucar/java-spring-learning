package org.gucardev.utilities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableFeignClients
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class UtilitiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtilitiesApplication.class, args);
    }

}
