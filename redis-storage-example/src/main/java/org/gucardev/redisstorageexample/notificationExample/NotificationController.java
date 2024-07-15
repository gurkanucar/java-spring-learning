package org.gucardev.redisstorageexample.notificationExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send")
    public UserNotification sendNotification(@RequestBody UserNotification userNotification) {
        return notificationService.sendOrUpdateNotification(userNotification);
    }

    @PostMapping("/update-delivered")
    public UserNotification updateDelivered(@RequestParam String username) {
        return notificationService.updateNotificationDelivered(username);
    }

    @GetMapping("/not-delivered")
    public List<UserNotification> getNotDeliveredNotifications() {
        return notificationService.getAllNotDeliveredNotifications();
    }


    @GetMapping("/name/{userName}")
    public List<UserNotification> getByName(@PathVariable String userName) {
        return notificationService.findAllByUsername(userName);
    }

    @GetMapping("/surname/{surname}")
    public List<UserNotification> getBySurName(@PathVariable String surname) {
        return notificationService.findAllBySurName(surname);
    }
}
