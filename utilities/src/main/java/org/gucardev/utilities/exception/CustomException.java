package org.gucardev.utilities.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
  private final HttpStatus status;

  public CustomException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  public CustomException(ExceptionMessage exception) {
    super(exception.getKey());
    this.status = exception.getStatus();
  }

  public HttpStatus getStatus() {
    return this.status;
  }
}
