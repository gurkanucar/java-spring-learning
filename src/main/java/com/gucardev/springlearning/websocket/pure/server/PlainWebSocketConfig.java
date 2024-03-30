package com.gucardev.springlearning.websocket.pure.server;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class PlainWebSocketConfig implements WebSocketConfigurer {


  private final WebSocketSessionRegistry webSocketSessionRegistry;
  private final Gson gson;


  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(new CustomWebSocketHandler(webSocketSessionRegistry,gson), "/ws")
            .setAllowedOriginPatterns("*");
  }
}
