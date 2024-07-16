package org.gucardev.projectmicro.security.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.gucardev.projectmicro.security.dto.LoginRequest;
import org.gucardev.projectmicro.security.dto.TokenDto;
import org.gucardev.projectmicro.security.remote.AuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;


@Configuration
@Component
@Slf4j
public class InternalFeignConfig {

    @Value("${app.service-token.username}")
    private String username;

    @Value("${app.service-token.password}")
    private String password;

    @Autowired
    private AuthClient authClient;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            try {
                HttpServletRequest currentRequest = getCurrentRequest();
                String authenticatedToken = currentRequest.getHeader("X-Authenticated-Token");
                String authenticatedUser = currentRequest.getHeader("X-Authenticated-User");

                if (authenticatedToken != null && authenticatedUser != null) {
                    requestTemplate.header("X-Authenticated-Token", authenticatedToken);
                    requestTemplate.header("X-Authenticated-User", authenticatedUser);
                } else {
                    // Retrieve new service token if headers are not present or invalid
                    log.error("Authentication failed");
                    retrieveToken(requestTemplate);
                }
            } catch (NullPointerException e) {
                retrieveToken(requestTemplate);
            }
        };
    }

    private void retrieveToken(RequestTemplate requestTemplate) {
        ResponseEntity<TokenDto> response = authClient.generateServiceToken(new LoginRequest(username, password));
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            requestTemplate.header("X-Authenticated-Token", response.getBody().getAccessToken());
            requestTemplate.header("X-Authenticated-User", username);
        }
    }

    private HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}


