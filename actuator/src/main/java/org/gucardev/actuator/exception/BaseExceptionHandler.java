package org.gucardev.actuator.exception;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
@Slf4j
public abstract class BaseExceptionHandler {

    private static final String TRACE_ID_LOG_VAR_NAME = "traceId";

    protected final ResponseEntity<ExceptionResponse> buildErrorResponse(Object error, HttpStatus status, WebRequest request) {
        String path = request.getDescription(false).replace("uri=", "");
        String queryParams = request.getParameterMap().entrySet().stream()
                .map(entry -> entry.getKey() + "=" + String.join(",", entry.getValue()))
                .collect(Collectors.joining("&"));

        if (!queryParams.isEmpty()) {
            path += "?" + queryParams;
        }
        Map<String, String> validationErrors = null;
        if (error instanceof Map) {
            validationErrors = (Map<String, String>) error;
            error = "Validation errors occurred.";
            //log.warn("Validation error on path: {} | validation errors: {}", path, validationErrors);
        }
        //else {
        //log.error("Controller Error | path: {} | message: {}", path, error);
        //}
        return ExceptionResponse.buildResponse(status, error, path, MDC.get(TRACE_ID_LOG_VAR_NAME), validationErrors);
    }
}

