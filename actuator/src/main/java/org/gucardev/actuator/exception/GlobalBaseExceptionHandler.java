package org.gucardev.actuator.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Order(Integer.MIN_VALUE)
@RestControllerAdvice
public class GlobalBaseExceptionHandler extends BaseExceptionHandler {

    public GlobalBaseExceptionHandler() {
    }

    @ExceptionHandler({CustomException.class})
    @Order(Integer.MIN_VALUE)
    public ResponseEntity<ExceptionResponse> generalException(CustomException exception, WebRequest request) {
        return this.buildErrorResponse(exception.getMessage(), exception.getStatus(), request);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public final ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidEx(
            MethodArgumentNotValidException ex, WebRequest request) {
        return this.getMapResponseEntity(ex, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public final ResponseEntity<ExceptionResponse> handleConstraintViolationEx(
            MethodArgumentNotValidException ex, WebRequest request) {
        return this.getMapResponseEntity(ex, request);
    }

    private ResponseEntity<ExceptionResponse> getMapResponseEntity(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(
                        (x) -> {
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

    @ExceptionHandler({Exception.class})
    public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request) {
        return this.buildErrorResponse(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public final ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(
            Exception ex, WebRequest request) {
        return this.buildErrorResponse("Required request body is missing", HttpStatus.BAD_REQUEST, request);
    }
}
