package org.gucardev.actuator;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    public String login(@Valid @RequestBody LoginRequest loginRequest) {
        if (Math.random() > 0.5) throw new RuntimeException("random error");
        return loginRequest.username;
    }

    @Getter
    @Setter
    static class LoginRequest {
        @NotNull
        private String username;
        @NotNull
        private String password;
    }
}

