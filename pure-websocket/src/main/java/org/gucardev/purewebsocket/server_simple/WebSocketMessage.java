package org.gucardev.purewebsocket.server_simple;

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
