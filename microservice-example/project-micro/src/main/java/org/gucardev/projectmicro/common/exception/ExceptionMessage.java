package org.gucardev.projectmicro.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionMessage {
    DEFAULT_EXCEPTION("messages.error.default_message", HttpStatus.BAD_REQUEST),
    ALREADY_EXISTS_EXCEPTION("messages.error.already_exists_exception", HttpStatus.BAD_REQUEST),
    NOT_FOUND_EXCEPTION("messages.error.not_found_exception", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND_EXCEPTION("messages.error.user_not_found_exception", HttpStatus.NOT_FOUND),
    ACCESS_DENIED_EXCEPTION("messages.error.access_denied_exception", HttpStatus.UNAUTHORIZED),
    TOKEN_IN_BLACKLIST("messages.error.token_in_blacklist", HttpStatus.UNAUTHORIZED),
    FORBIDDEN_EXCEPTION("messages.error.forbidden_exception", HttpStatus.FORBIDDEN);

    private final String key;
    private final HttpStatus status;

    ExceptionMessage(String key, HttpStatus httpStatus) {
        this.key = key;
        this.status = httpStatus;
    }

}
