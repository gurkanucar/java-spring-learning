package org.gucardev.purewebsocket.server_simple;


import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomWebSocketHandler extends TextWebSocketHandler {

    private final WebSocketSessionRegistry webSocketSessionRegistry;
    private final Gson gson;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("new session connected {}", session.toString());
        webSocketSessionRegistry.addSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("new session disconnected {}", session.toString());
        webSocketSessionRegistry.removeSession(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.info("message: {}", message.getPayload());
    }

    public <T> void sendMessage(String route, T payload) {
        WebSocketMessage<T> message = new WebSocketMessage<>(route, payload);
        if (webSocketSessionRegistry.getSessions().isEmpty()) {
            log.warn("No sessions available to send message");
        }
        webSocketSessionRegistry
                .getSessions()
                .forEach(
                        (session) -> {
                            try {
                                String jsonMessage = gson.toJson(message);
                                broadcastMessageToAll(jsonMessage);
                                log.info("Message sent to session: {}", session);
                            } catch (Exception e) {
                                log.error("JSON processing error: {}", e.getMessage(), e);
                            }
                        });
    }

    private void broadcastMessageToAll(String message) throws IOException {
        for (WebSocketSession webSocketSession : webSocketSessionRegistry.getSessions()) {
            webSocketSession.sendMessage(new TextMessage(message));
        }
    }

    private void broadcastMessageToOthers(WebSocketSession sender, String message)
            throws IOException {
        for (WebSocketSession webSocketSession : webSocketSessionRegistry.getSessions()) {
            if (!webSocketSession.getId().equals(sender.getId())) {
                webSocketSession.sendMessage(new TextMessage(message));
            }
        }
    }

    private void sendBackToSender(WebSocketSession session, String message) throws IOException {
        session.sendMessage(new TextMessage(message));
    }

    private void handleListSessions(WebSocketSession session) throws IOException {
        log.info(Arrays.toString(webSocketSessionRegistry.getSessions().toArray()));
        var array =
                webSocketSessionRegistry.getSessions().stream().map(WebSocketSession::getId).toArray();
        String sessionIds = Arrays.toString(array);
        session.sendMessage(new TextMessage("clients: %d | %s".formatted(array.length, sessionIds)));
    }
}
