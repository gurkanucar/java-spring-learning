package org.gucardev.redisstorageexample.notificationExample;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {


    private final NotificationRepository notifactionRepository;

    public NotificationService(NotificationRepository notifactionRepository) {
        this.notifactionRepository = notifactionRepository;
    }

    public UserNotification sendOrUpdateNotification(UserNotification userNotification) {
        Optional<UserNotification> existingNotification = notifactionRepository.findByUsername(userNotification.getUsername());
        if (existingNotification.isPresent()) {
            UserNotification existing = existingNotification.get();
            existing.setLastNotificationTime(userNotification.getLastNotificationTime());
            existing.setNotificationDelivered(userNotification.getNotificationDelivered());
            return notifactionRepository.save(existing);
        }
        return notifactionRepository.save(userNotification);
    }

    public UserNotification updateNotificationDelivered(String username) {
        Optional<UserNotification> maybeUserNotification = notifactionRepository.findByUsername(username);
        if (maybeUserNotification.isPresent()) {
            UserNotification userNotification = maybeUserNotification.get();
            userNotification.setNotificationDelivered(true);
            return notifactionRepository.save(userNotification);
        }
        return null;
    }

    public List<UserNotification> getAllNotDeliveredNotifications() {
        return notifactionRepository.findByNotificationDeliveredFalse();
    }

    public List<UserNotification> findAllByUsername(String userName) {
        return notifactionRepository.findAllByUsername(userName);
    }

    public List<UserNotification> findAllBySurName(String surname) {
        return notifactionRepository.findAllBySurname(surname);
    }
}
