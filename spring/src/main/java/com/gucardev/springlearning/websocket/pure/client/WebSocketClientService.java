package com.gucardev.springlearning.websocket.pure.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class WebSocketClientService {

    @Autowired
    private MyWebSocketClient webSocketClient;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public void sendData() {
        Future<?> future = executor.submit(() -> {
            try {
                if (!webSocketClient.isOpen()) {
                    webSocketClient.connectBlocking();
                }

                if (webSocketClient.isOpen()) {
                    webSocketClient.send("ls");
                } else {
                    System.out.println("Connection timeout");
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        try {
            future.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            future.cancel(true);
            System.out.println("Connection timeout");
        }
    }
}

