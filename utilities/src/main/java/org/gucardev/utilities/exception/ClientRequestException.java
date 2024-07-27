package org.gucardev.utilities.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ClientRequestException extends RuntimeException {
  private final HttpStatus status;

  public ClientRequestException(String message) {
    super(message);
    this.status = HttpStatus.INTERNAL_SERVER_ERROR;
  }

}
