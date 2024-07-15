package org.gucardev.strategypatternwithenums.with_enums.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SMSHelper {
    public void sendSMS(String to, String body) {
        log.info("Sending sms to: {},  body: {}", to, body);
    }
}
