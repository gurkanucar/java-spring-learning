package com.gucardev.springlearning.enum_service;

import com.gucardev.springlearning.enum_service.service.EmailNotificationService;
import com.gucardev.springlearning.enum_service.service.NotificationService;
import com.gucardev.springlearning.enum_service.service.SMSNotificationService;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum NotificationType {
    EMAIL(EmailNotificationService.class), SMS(SMSNotificationService.class);

    private final Class<? extends NotificationService> service;

    NotificationType(Class<? extends NotificationService> notificationServiceClass) {
        this.service = notificationServiceClass;
    }

    public NotificationService getService() {
        return SpringContext.getApplicationContext(service);
    }

    public static NotificationType fromType(String name) {
        return Arrays.stream(NotificationType.values())
                .filter(x -> x.name().equalsIgnoreCase(name))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid NotificationTypes name: " + name));
    }
}
