package org.gucardev.strategypatternwithenums.with_enums.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EMailHelper {
    public void sendMail(String to, String subject, String body) {
        log.info("Sending mail to: {}, subject: {}, body: {}", to, subject, body);
    }
}
