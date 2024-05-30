package com.gucardev.springlearning.feign_transaction.aspect;

import java.util.ArrayList;
import java.util.List;

public class RequestDetailsHolder {
    private static final ThreadLocal<List<RequestDetails>> requestDetailsThreadLocal = new ThreadLocal<>();

    public static void addRequestDetails(RequestDetails details) {
        if (requestDetailsThreadLocal.get() == null) {
            requestDetailsThreadLocal.set(new ArrayList<>());
        }
        requestDetailsThreadLocal.get().add(details);
    }

    public static List<RequestDetails> getRequestDetails() {
        return requestDetailsThreadLocal.get();
    }

    public static void clear() {
        requestDetailsThreadLocal.remove();
    }
}

