package org.gucardev.strategypatternwithenums;

import lombok.Getter;
import org.gucardev.strategypatternwithenums.service.EmailNotificationService;
import org.gucardev.strategypatternwithenums.service.NotificationService;
import org.gucardev.strategypatternwithenums.service.SMSNotificationService;

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
