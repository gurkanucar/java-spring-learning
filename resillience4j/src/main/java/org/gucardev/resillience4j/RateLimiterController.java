package org.gucardev.resillience4j;


import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping
public class RateLimiterController {

    @GetMapping("/hello")
    public String hello() {
        log.info("hello");
        return "Hello World";
    }

    @RateLimiter(name = "auth", fallbackMethod = "ratelimiterFallback")
    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(loginRequest);
    }

    public ResponseEntity<?> ratelimiterFallback(Throwable throwable) {
        return ResponseEntity.ok("Rate limiter fallback: Too many requests.");
    }

    @GetMapping("/get_user")
    @RateLimiter(name = "endpoint-1", fallbackMethod = "getUserFallback")
    public String getUser(@RequestParam("id") Integer id) {
        return "User:" + id;
    }

    public String getUserFallback(Integer id, Throwable throwable) {
        log.info("[getUserFallback][id({}) exception({})]", id, throwable.getClass().getSimpleName());
        return "mock:User:" + id;
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

