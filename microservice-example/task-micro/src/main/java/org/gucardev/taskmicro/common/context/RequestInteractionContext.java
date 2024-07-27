package org.gucardev.taskmicro.common.context;

import org.springframework.stereotype.Component;

@Component
public class RequestInteractionContext {

    private static final ThreadLocal<Boolean> context = new ThreadLocal<>();

    public void setRequestWithoutInteractionTrue() {
        context.set(true);
    }

    public boolean isRequestWithoutInteraction() {
        Boolean contextValue = context.get();
        return !(contextValue == null || !contextValue);
    }

    public void clearContext() {
        context.set(false);
    }
}
