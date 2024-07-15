package org.gucardev.utilities.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public abstract class BaseExceptionHandler {
    public BaseExceptionHandler() {
    }

    protected final ResponseEntity<ExceptionResponse> buildErrorResponse(Object error, HttpStatus status, WebRequest request) {
        String path = request.getDescription(false).replace("uri=", "");
        return ExceptionResponse.builder().error(error).status(status).path(path).build();
    }
}
