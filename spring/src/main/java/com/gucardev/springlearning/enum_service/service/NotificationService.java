package com.gucardev.springlearning.enum_service.service;

import com.gucardev.springlearning.enum_service.dto.NotificationDto;

public interface NotificationService {

    void sendNotification(NotificationDto notificationDto);

}
