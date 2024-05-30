package org.gucardev.event_bus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalEventListener {
  private final EventBus eventBus;

  public InternalEventListener(EventBus eventBus) {
    this.eventBus = eventBus;
    eventBus.register(this);
  }

  @Subscribe
//  @AllowConcurrentEvents
  public void handleCustomEvent(CustomEvent event) {
    if (event.getEventTo().equals(EventTo.EXTERNAL)) return;
    log.info("Received INTERNAL event for route '{}': {}", event.getRoute(), event.getProductName());
  }
}
