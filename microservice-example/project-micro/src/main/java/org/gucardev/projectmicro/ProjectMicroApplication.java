package org.gucardev.projectmicro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ProjectMicroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectMicroApplication.class, args);
    }

}
