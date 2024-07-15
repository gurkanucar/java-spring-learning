package org.gucardev.analyticsmicro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AnalyticsMicroApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalyticsMicroApplication.class, args);
    }

}
