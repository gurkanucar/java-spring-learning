package com.gucardev.springlearning.enum_service.controller;

import com.gucardev.springlearning.enum_service.NotificationType;
import com.gucardev.springlearning.enum_service.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
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
