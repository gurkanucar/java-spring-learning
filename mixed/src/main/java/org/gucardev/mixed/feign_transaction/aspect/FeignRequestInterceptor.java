package org.gucardev.mixed.feign_transaction.aspect;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Map;
import java.util.stream.Collectors;

public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        if (template.methodMetadata().method().isAnnotationPresent(RollbackApi.class)) {
            RequestDetails details = new RequestDetails();
            details.setBaseUrl(template.feignTarget().url());
            details.setUrl(template.url());
            details.setMethod(template.method());
            details.setHeaders(template.headers().entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> String.join(", ", e.getValue()))));
            details.setBody(template.requestBody().asString());
            details.setQueryParams(template.queries());
            RequestDetailsHolder.addRequestDetails(details);
        }
    }
}
