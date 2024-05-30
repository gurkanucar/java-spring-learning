package org.gucardev.event_bus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

class MainTest {

  //  @Test
  //  void mainTest() {
  //    Logger logger = (Logger) LoggerFactory.getLogger(Main.class);
  //    ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
  //    listAppender.start();
  //    logger.addAppender(listAppender);
  //
  //    Main.sayHello();
  //
  //    List<ILoggingEvent> logsList = listAppender.list;
  //
  //    assertEquals("selam", logsList.get(0).getMessage());
  //    assertEquals(Level.INFO, logsList.get(0).getLevel());
  //  }

  @Test
  void mainTest() {
    Logger mainLogger = (Logger) LoggerFactory.getLogger(Main.class);
    ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
    listAppender.start();
    mainLogger.addAppender(listAppender);
    Logger otherLogger = (Logger) LoggerFactory.getLogger(InternalEventListener.class);
    otherLogger.addAppender(listAppender);
    Logger otherLogger2 = (Logger) LoggerFactory.getLogger(ExternalEventListener.class);
    otherLogger2.addAppender(listAppender);

    Main.start();
    Main.eventBus.post(new CustomEvent(EventTo.EXTERNAL, "closeProductReq", "product2"));

    List<ILoggingEvent> logsList = listAppender.list;

    assertTrue(logsList.get(1).getFormattedMessage().contains("Received INTERNAL event for route 'closeProductRes': product1"));
  }
}
