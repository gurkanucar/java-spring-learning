package org.gucardev.event_bus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class ExternalEventListener {
  private final EventBus eventBus;
  private final Random random = new Random();
  public ExternalEventListener(EventBus eventBus) {
    this.eventBus = eventBus;
    eventBus.register(this);
  }

  @Subscribe
//  @AllowConcurrentEvents
  public void handleCustomEvent(CustomEvent event) {
    if (event.getEventTo().equals(EventTo.INTERNAL)) return;
    log.info("Received EXTERNAL event for route '{}': {}", event.getRoute(), event.getProductName());

    int randomDelay = 0;//5000 + random.nextInt(5000);

    try {
      Thread.sleep(randomDelay);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      log.error("Thread interrupted while sleeping for delay.", e);
    }
    eventBus.post(new CustomEvent(EventTo.INTERNAL, "closeProductRes", event.getProductName()));
  }
}
