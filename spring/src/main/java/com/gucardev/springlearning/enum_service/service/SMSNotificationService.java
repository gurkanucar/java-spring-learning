package com.gucardev.springlearning.enum_service.service;

import com.gucardev.springlearning.enum_service.dto.NotificationDto;
import com.gucardev.springlearning.enum_service.helper.SMSHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SMSNotificationService implements NotificationService {

    private final SMSHelper smsHelper;

    @Override
    public void sendNotification(NotificationDto notificationDto) {
        log.info("Sending sms notification -> {}", notificationDto.getMessage());
        smsHelper.sendSMS("user", "message");
    }
}
