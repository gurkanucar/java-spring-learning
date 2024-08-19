package org.gucardev.actuator;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping
public class Controller {

    @GetMapping("/hello")
    public String hello() {
        log.info("hello");
        return "Hello World";
    }

    @PostMapping("/api/auth/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        if (Math.random() > 0.5) throw new RuntimeException("");
        return loginRequest.username;
    }

    @Getter
    @Setter
    static class LoginRequest {
        private String username;
        private String password;
    }
}

