package org.gucardev.projectmicro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@EnableFeignClients
@SpringBootApplication
public class ProjectMicroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectMicroApplication.class, args);
    }

}
