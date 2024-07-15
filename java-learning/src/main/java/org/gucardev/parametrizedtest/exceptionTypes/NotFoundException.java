package org.gucardev.parametrizedtest.exceptionTypes;

public class NotFoundException extends RuntimeException {
  private final String message;

  public NotFoundException(String message) {
    this.message = message;
  }
}
