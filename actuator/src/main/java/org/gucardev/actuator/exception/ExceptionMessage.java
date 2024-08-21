package org.gucardev.actuator.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionMessage {
    DEFAULT_EXCEPTION("something went wrong!", HttpStatus.BAD_REQUEST),
    ALREADY_EXISTS_EXCEPTION("Record already exists!", HttpStatus.BAD_REQUEST),
    NOT_FOUND_EXCEPTION("[{0}] Not found!", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND_EXCEPTION("The user [{0}] was not found in system", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus status;

    ExceptionMessage(String message, HttpStatus httpStatus) {
        this.message = message;
        this.status = httpStatus;
    }

}
