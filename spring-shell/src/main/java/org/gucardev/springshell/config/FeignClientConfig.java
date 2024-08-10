package org.gucardev.springshell.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor(@Value("${api.key}") String apiKey) {
        return requestTemplate -> requestTemplate.header("authorization", "apikey " + apiKey);
    }
}
