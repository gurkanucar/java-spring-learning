package org.gucardev.mixed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableFeignClients
@EnableAspectJAutoProxy(proxyTargetClass=true)
@SpringBootApplication
public class MixedApplication {

    public static void main(String[] args) {
        SpringApplication.run(MixedApplication.class, args);
    }

}
