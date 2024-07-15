package org.gucardev.strategypatternwithenums.controller;


import lombok.RequiredArgsConstructor;
import org.gucardev.strategypatternwithenums.NotificationType;
import org.gucardev.strategypatternwithenums.dto.NotificationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {

    @PostMapping
    public ResponseEntity<?> sendNotification(@RequestBody NotificationDto notification) {
        NotificationType.fromType(notification.getNotificationType()).getService().sendNotification(notification);
        return ResponseEntity.ok().build();
    }

}
