package org.gucardev.mixed.feign_transaction;

import org.gucardev.mixed.feign_transaction.aspect.FeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
