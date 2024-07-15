package org.gucardev.purewebsocket.server_with_security;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class NotificationsController {
  private final WebSocketSessionService sessionService;

  @PostMapping("/notifications/{user}")
  public void createNotification(@PathVariable String user, @RequestBody String notification)
      throws IOException {
    if (notification == null) {
      throw new IllegalArgumentException("Notification should not be null");
    }

    var session = sessionService.getSession(user);
    if (session == null) {
      throw new IllegalStateException(user + " is not connected");
    }

    session.sendMessage(new TextMessage(notification));
  }
}
