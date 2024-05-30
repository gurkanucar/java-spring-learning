package org.gucardev.log_test;

import static org.junit.jupiter.api.Assertions.*;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

class MainTest {

  @Test
  void mainTest() {
    Logger logger = (Logger) LoggerFactory.getLogger(Main.class);
    ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
    listAppender.start();
    logger.addAppender(listAppender);

    Main.sayHello();

    List<ILoggingEvent> logsList = listAppender.list;

    assertEquals("selam", logsList.get(0).getMessage());
    assertEquals(Level.INFO, logsList.get(0).getLevel());
  }
}
