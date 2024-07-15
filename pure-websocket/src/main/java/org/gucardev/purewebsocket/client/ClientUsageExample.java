package org.gucardev.purewebsocket.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientUsageExample {

    private final WebSocketClientService webSocketClientService;

    public void sendMessage(String message) {
        webSocketClientService.sendData(message);
    }

}
