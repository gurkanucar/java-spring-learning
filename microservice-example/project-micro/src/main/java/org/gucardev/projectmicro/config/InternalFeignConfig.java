package org.gucardev.projectmicro.config;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Configuration
public class InternalFeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            // Extract the headers from the current request
            String authenticatedToken = getCurrentRequest().getHeader("X-Authenticated-Token");
            String authenticatedUser = getCurrentRequest().getHeader("X-Authenticated-User");

            // Add headers to the Feign request
            if (authenticatedToken != null) {
                requestTemplate.header("X-Authenticated-Token", authenticatedToken);
            }
            if (authenticatedUser != null) {
                requestTemplate.header("X-Authenticated-User", authenticatedUser);
            }
        };
    }

    private HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}

