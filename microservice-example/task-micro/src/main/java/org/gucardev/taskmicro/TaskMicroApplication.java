package org.gucardev.taskmicro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TaskMicroApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskMicroApplication.class, args);
    }

}
