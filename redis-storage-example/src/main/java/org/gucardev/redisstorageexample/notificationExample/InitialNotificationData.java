package org.gucardev.redisstorageexample.notificationExample;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
@RequiredArgsConstructor
public class InitialNotificationData implements CommandLineRunner {

    private final NotificationRepository notificationRepository;

    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();
        for (int i = 1; i <= 20; i++) {
            UserNotification userNotification = new UserNotification();
            userNotification.setUsername("user" + i);
            userNotification.setSurname("sur" + i);
            userNotification.setLastNotificationTime(System.currentTimeMillis() - random.nextInt(1000000));
            userNotification.setNotificationDelivered(random.nextBoolean());
            notificationRepository.save(userNotification);
        }

        System.out.println("Sample data generated and saved to Redis.");
    }
}
