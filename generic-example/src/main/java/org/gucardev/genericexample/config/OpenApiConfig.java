package org.gucardev.genericexample.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(description = "OpenApi documentation", title = "project", version = "1.0")
)
@Configuration
public class OpenApiConfig {
}
