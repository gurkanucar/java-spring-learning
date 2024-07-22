package org.gucardev.analyticsmicro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAspectJAutoProxy
@EnableFeignClients
@SpringBootApplication
@EnableScheduling
public class AnalyticsMicroApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalyticsMicroApplication.class, args);
    }

}
