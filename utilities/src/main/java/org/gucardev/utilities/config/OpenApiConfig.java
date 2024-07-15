package org.gucardev.utilities.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(description = "OpenApi documentation", title = "Utility", version = "1.0"),
        security = {@SecurityRequirement(name = "bearerAuth")}
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
@Configuration
public class OpenApiConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("add-custom-header")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    HeaderParameter headerParam = (HeaderParameter) new HeaderParameter()
                            .name("X-TRACE-ID")
                            .description("Trace Identifier")
                            .required(true)
                            .schema(new StringSchema().example("default-trace-id"));
                    HeaderParameter headerParam2 = (HeaderParameter) new HeaderParameter()
                            .name("X-COMPANY-ID")
                            .description("Trace Identifier")
                            .required(true)
                            .schema(new StringSchema().example("default-company-id"));
                    operation.addParametersItem(headerParam);
                    operation.addParametersItem(headerParam2);
                    return operation;
                })
                .build();
    }
}
