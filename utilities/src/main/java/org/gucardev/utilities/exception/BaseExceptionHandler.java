package org.gucardev.utilities.exception;

import lombok.NoArgsConstructor;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

@NoArgsConstructor
public abstract class BaseExceptionHandler {

    private static final String TRACE_ID_LOG_VAR_NAME = "traceId";

    protected final ResponseEntity<ExceptionResponse> buildErrorResponse(Object error, HttpStatus status, WebRequest request) {
        String path = request.getDescription(false).replace("uri=", "");
        return ExceptionResponse.builder().error(error).status(status).path(path).traceId(MDC.get(TRACE_ID_LOG_VAR_NAME)).build();
    }
}
