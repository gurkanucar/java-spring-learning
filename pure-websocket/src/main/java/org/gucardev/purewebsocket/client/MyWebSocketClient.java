package org.gucardev.purewebsocket.client;

import jakarta.annotation.PostConstruct;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class MyWebSocketClient extends WebSocketClient {

    public MyWebSocketClient(@Value("${websocket.url.for-client}") String url) throws URISyntaxException {
        super(new URI(url));
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("Opened connection");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Closed connection");
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    @PostConstruct
    public void init() {

    }
}