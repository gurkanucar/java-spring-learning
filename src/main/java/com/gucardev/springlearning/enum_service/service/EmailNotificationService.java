package com.gucardev.springlearning.enum_service.service;

import com.gucardev.springlearning.enum_service.dto.NotificationDto;
import com.gucardev.springlearning.enum_service.helper.EMailHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailNotificationService implements NotificationService {

    private final EMailHelper mailHelper;

    @Override
    public void sendNotification(NotificationDto notificationDto) {
        log.info("Sending email notification -> {}", notificationDto.getMessage());
        mailHelper.sendMail("user", "subj", "message");
    }
}
