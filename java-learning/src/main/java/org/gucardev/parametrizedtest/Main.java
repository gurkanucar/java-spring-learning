package org.gucardev.parametrizedtest;

import org.gucardev.parametrizedtest.exceptionTypes.AlreadyExistsException;
import org.gucardev.parametrizedtest.exceptionTypes.CouldNotCompletedException;
import org.gucardev.parametrizedtest.exceptionTypes.ErrorType;
import org.gucardev.parametrizedtest.exceptionTypes.NotFoundException;

public class Main {
  public static void main(String[] args) {
  }

  public void doSomethingAndThrowError(ErrorType errorType) {
    switch (errorType) {
      case NOT_FOUND -> throw new NotFoundException("not found!");
      case ALREADY_EXISTS -> throw new AlreadyExistsException("already exists!");
      case COULD_NOT_COMPLETED -> throw new CouldNotCompletedException("already exists!");
    }
  }
}
