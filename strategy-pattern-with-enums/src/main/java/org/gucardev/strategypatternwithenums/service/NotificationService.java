package org.gucardev.strategypatternwithenums.service;


import org.gucardev.strategypatternwithenums.dto.NotificationDto;

public interface NotificationService {

    void sendNotification(NotificationDto notificationDto);

}
