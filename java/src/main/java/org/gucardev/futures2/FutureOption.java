package org.gucardev.futures2;

public class FutureOption<T, U> {

  private final T method;
  private final U exception;

  public FutureOption(T method, U exception) {
    this.method = method;
    this.exception = exception;
  }

  public T getMethod() {
    return method;
  }

  public U getException() {
    return exception;
  }
}
