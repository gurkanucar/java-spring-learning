package org.gucardev.taskmicro.common.remote;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.gucardev.taskmicro.common.context.RequestInteractionContext;
import org.gucardev.taskmicro.common.security.dto.LoginRequest;
import org.gucardev.taskmicro.common.security.dto.TokenDto;
import org.gucardev.taskmicro.common.security.remote.AuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Configuration
public class FeignConfig {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    @Autowired
    private RequestInteractionContext requestInteractionContext;

    @Value("${app.service-token.username}")
    private String username;

    @Value("${app.service-token.password}")
    private String password;

    @Autowired
    private AuthClient authClient;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            if (!requestInteractionContext.isRequestWithoutInteraction()) {
                HttpServletRequest currentRequest = getCurrentRequest();
                String authenticatedToken = currentRequest.getHeader(AUTHORIZATION);
                if (authenticatedToken != null) {
                    requestTemplate.header(AUTHORIZATION, authenticatedToken);
                    log.info("relay token");
                }
            } else {
                log.info("generate token");
                String serviceToken = getServiceToken();
                requestTemplate.header("Authorization", "Bearer " + serviceToken);
            }
        };
    }


    private String getServiceToken() {
        ResponseEntity<TokenDto> response = authClient.generateServiceToken(new LoginRequest(username, password));
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().getAccessToken();
        }
        throw new RuntimeException("Failed to generate token");
    }

    private HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(org.springframework.web.context.request.RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
