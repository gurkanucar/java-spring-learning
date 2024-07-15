package org.gucardev.utilities.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseExceptionHandler {
    public BaseExceptionHandler() {
    }

    protected final ResponseEntity<ExceptionResponse> buildErrorResponse(Object error, HttpStatus status) {
        return ExceptionResponse.builder().error(error).status(status).build();
    }
}
