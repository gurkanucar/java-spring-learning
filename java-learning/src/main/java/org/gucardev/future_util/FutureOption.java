package org.gucardev.future_util;

import lombok.Getter;

@Getter
public class FutureOption<T, U> {

  private final T method;
  private final U exception;

  public FutureOption(T method, U exception) {
    this.method = method;
    this.exception = exception;
  }

}
