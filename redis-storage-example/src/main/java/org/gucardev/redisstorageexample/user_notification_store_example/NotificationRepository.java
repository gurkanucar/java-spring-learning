package org.gucardev.redisstorageexample.user_notification_store_example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends CrudRepository<UserNotification, Long> {
    Optional<UserNotification> findByUsername(String username);

    List<UserNotification> findByNotificationDeliveredFalse();

    List<UserNotification> findAllByUsername(String userName);

    List<UserNotification> findAllBySurname(String surname);
}
