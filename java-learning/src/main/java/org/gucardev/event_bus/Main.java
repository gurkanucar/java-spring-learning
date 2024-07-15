package org.gucardev.event_bus;

import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

  public static EventBus eventBus;

  public static void main(String[] args) {
    sayHello();
    start();
  }

  public static void sayHello() {
    log.info("selam");
  }

  public static void start() {
    eventBus = new EventBus();
    new InternalEventListener(eventBus);
    new ExternalEventListener(eventBus);
    eventBus.post(new CustomEvent(EventTo.EXTERNAL, "closeProductReq", "product1"));
  }
}
