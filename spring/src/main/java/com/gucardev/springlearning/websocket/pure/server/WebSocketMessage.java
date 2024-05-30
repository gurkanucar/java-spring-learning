package com.gucardev.springlearning.websocket.pure.server;

import lombok.Data;

@Data
public class WebSocketMessage<T> {
    private String route;
    private T payload;

    public WebSocketMessage(String route, T payload) {
        this.route = route;
        this.payload = payload;
    }

}
