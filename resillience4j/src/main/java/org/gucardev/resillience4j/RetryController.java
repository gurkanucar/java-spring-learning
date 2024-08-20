package org.gucardev.resillience4j;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/retry")
public class RetryController {

    @GetMapping("/get_user")
    @Retry(name = "my-service-retry", fallbackMethod = "getUserFallback")
    public ResponseEntity<Map<String, String>> getUser() {
        if (Math.random() > 0.5) {
            log.error("random exception");
            throw new RuntimeException("random exception");
        }
        return ResponseEntity.ok(Map.of("username", "john"));
    }

    public ResponseEntity<?> getUserFallback(Throwable throwable) {
        var err = " [getUserFallback][exception(%s)]".formatted(throwable.getClass().getSimpleName());
        log.error(err);
        return ResponseEntity.badRequest().body(err);
    }

}
