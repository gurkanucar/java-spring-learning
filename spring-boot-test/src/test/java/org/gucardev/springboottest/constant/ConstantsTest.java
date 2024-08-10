package org.gucardev.springboottest.constant;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class ConstantsTest {

  @Test
  void testPrivateConstructor()
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
          InstantiationException {
    Constructor<Constants> constructor = Constants.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    constructor.setAccessible(true);
    assertNotNull(constructor.newInstance());
  }

  @Test
  void testConstants() {
    assertEquals("error", Constants.EXCEPTION_MESSAGE_KEY);
  }
}
