package org.gucardev.actuator.exception;

import lombok.Getter;

@Getter
public enum ResponseConstants {
  SUCCESS(0, "Success"),
  FAILURE(-1, "Failure");

  final int code;
  final String message;

  ResponseConstants(int code, String message) {
    this.code = code;
    this.message = message;
  }

}
