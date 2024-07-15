package org.gucardev.parametrizedtest.exceptionTypes;

public class AlreadyExistsException extends RuntimeException {
  private final String message;

  public AlreadyExistsException(String message) {
    this.message = message;
  }
}
