package org.gucardev.springboottest.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotFoundExceptionTest {

  @Test
  void testNotFoundException() {
    String expectedMessage = "message";
    NotFoundException ex = new NotFoundException(expectedMessage);
    assertEquals(expectedMessage, ex.getMessage());
  }
}
