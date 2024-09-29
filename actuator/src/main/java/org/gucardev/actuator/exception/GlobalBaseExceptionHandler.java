package org.gucardev.actuator.exception;

import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalBaseExceptionHandler extends BaseExceptionHandler {

    public GlobalBaseExceptionHandler() {
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ExceptionResponse> handleCustomException(CustomException exception, WebRequest request) {
        return this.buildErrorResponse(exception.getMessage(), exception.getStatus(), request);
    }

    @ExceptionHandler({NoResourceFoundException.class})
    public ResponseEntity<ExceptionResponse> noResourceFoundException(CustomException exception, WebRequest request) {
        return this.buildErrorResponse(exception.getMessage(), exception.getStatus(), request);
    }

    @ExceptionHandler({Exception.class})
    public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request) {
        String message = ex.getLocalizedMessage();
        if (isDatabaseException(ex)) {
            log.error("Database exception occurred: ", ex);
            message = "Database error occurred. Please contact support.";
        } else {
            log.error("Exception occurred: ", ex);
        }
        return this.buildErrorResponse(message, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public final ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidEx(
            MethodArgumentNotValidException ex, WebRequest request) {
        return this.getMapResponseEntity(ex, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public final ResponseEntity<ExceptionResponse> handleConstraintViolationEx(
            ConstraintViolationException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations()
                .forEach(violation -> {
                    String fieldName = violation.getPropertyPath().toString();
                    String errorMessage = violation.getMessage();
                    errors.put(fieldName, errorMessage);
                });
        return this.buildErrorResponse(errors, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex, WebRequest request) {
        Throwable mostSpecificCause = ex.getMostSpecificCause();
        String missingFieldMessage = mostSpecificCause.getMessage();
        return this.buildErrorResponse("Required request body is missing. " + missingFieldMessage, HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<ExceptionResponse> getMapResponseEntity(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(x -> {
                    String errorMessage = x.getDefaultMessage();
                    String fieldName;
                    if (x instanceof FieldError) {
                        fieldName = ((FieldError) x).getField();
                        errors.put(fieldName, errorMessage);
                    } else {
                        fieldName = x.getObjectName();
                        errors.put(fieldName, errorMessage);
                    }
                });
        return this.buildErrorResponse(errors, HttpStatus.BAD_REQUEST, request);
    }

    private boolean isDatabaseException(Throwable ex) {
        return ex instanceof SQLException || ex instanceof DataAccessException || ex instanceof PersistenceException;
    }
}


