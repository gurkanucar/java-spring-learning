package org.gucardev.actuator;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.gucardev.actuator.exception.ExceptionMessage;
import org.springframework.web.bind.annotation.*;

import static org.gucardev.actuator.exception.ExceptionUtil.buildException;

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
        if (Math.random() > 0.5)
            throw buildException(ExceptionMessage.USER_NOT_FOUND_EXCEPTION, loginRequest.getUsername());
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

