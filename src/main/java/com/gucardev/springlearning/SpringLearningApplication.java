package com.gucardev.springlearning;

import com.gucardev.springlearning.websocket.pure.client.WebSocketClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SpringLearningApplication implements CommandLineRunner {

    @Autowired
    WebSocketClientService webSocketClientService;

    public static void main(String[] args) {
        SpringApplication.run(SpringLearningApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        webSocketClientService.sendData();
    }
}


