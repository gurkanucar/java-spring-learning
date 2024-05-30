package org.gucardev.parametrizedtest.exceptionTypes;

public class CouldNotCompletedException extends RuntimeException {
  private final String message;

  public CouldNotCompletedException(String message) {
    this.message = message;
  }
}
