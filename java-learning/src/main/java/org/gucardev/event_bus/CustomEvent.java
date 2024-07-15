package org.gucardev.event_bus;

import lombok.Data;

@Data
public class CustomEvent {
  private EventTo eventTo;
  private String route;
  private String productName;

  public CustomEvent(EventTo eventTo, String route, String productName) {
    this.eventTo = eventTo;
    this.route = route;
    this.productName = productName;
  }
}
