package org.gucardev.utilities.exception;

import org.springframework.http.HttpStatus;

public class ClientRequestException extends RuntimeException {
  private final HttpStatus status;

  public ClientRequestException(String message, HttpStatus status) {
    super(message);
    this.status = HttpStatus.INTERNAL_SERVER_ERROR;
  }

  public HttpStatus getStatus() {
    return this.status;
  }
}
