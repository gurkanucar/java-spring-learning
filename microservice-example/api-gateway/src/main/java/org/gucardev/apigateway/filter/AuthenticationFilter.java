package org.gucardev.apigateway.filter;


import lombok.extern.slf4j.Slf4j;
import org.gucardev.apigateway.dto.AuthResponse;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<Object> {

    private final WebClient.Builder webClientBuilder;

    public AuthenticationFilter(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Object config) {
        log.info("filter configured");
        return (exchange, chain) -> {
            String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                return webClientBuilder
                        .build()
                        .get()
                        .uri(uriBuilder -> uriBuilder.scheme("lb").host("auth-micro")
                                .path("/auth/validate").queryParam("token", token)
                                .build())
                        .retrieve()
                        .toEntity(AuthResponse.class)
                        .flatMap(
                                authResponse -> {
                                    if (authResponse.getStatusCode().is2xxSuccessful()) {
                                        AuthResponse authObj = authResponse.getBody();
                                        exchange
                                                .getRequest()
                                                .mutate()
                                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + authObj.getToken())
                                                .build();
                                        return chain.filter(exchange);
                                    } else {
                                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                                        return exchange.getResponse().setComplete();
                                    }
                                })
                        .onErrorResume(throwable -> {
                            log.error("Error during authentication", throwable);
                            return handleAuthenticationError(exchange);
                        });
            } else {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        };
    }

    private Mono<Void> handleAuthenticationError(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
