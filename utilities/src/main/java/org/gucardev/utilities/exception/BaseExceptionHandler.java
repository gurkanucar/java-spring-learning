package org.gucardev.utilities.exception;

import lombok.NoArgsConstructor;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@NoArgsConstructor
public abstract class BaseExceptionHandler {

    private static final String TRACE_ID_LOG_VAR_NAME = "traceId";

    protected final ResponseEntity<ExceptionResponse> buildErrorResponse(Object error, HttpStatus status, WebRequest request, Map<String, String> validationErrors) {
        String path = request.getDescription(false).replace("uri=", "");
        String traceId = MDC.get(TRACE_ID_LOG_VAR_NAME);
        return ExceptionResponse.buildResponse(
                status,
                error,
                path,
                traceId,
                validationErrors
        );
    }

    protected final ResponseEntity<ExceptionResponse> buildErrorResponse(Object error, HttpStatus status, WebRequest request) {
        return buildErrorResponse(error, status, request, null);
    }
}
