package org.gucardev.strategypatternwithenums.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gucardev.strategypatternwithenums.dto.NotificationDto;
import org.gucardev.strategypatternwithenums.helper.EMailHelper;
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
