package org.gucardev.awssesmailsending;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SesEmailSender {

    @Value("${mail.address}")
    private String mailAddress;

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Autowired
    public SesEmailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }

    public void sendEmail(String toEmail, String subject, String body) {
        SendEmailRequest request = new SendEmailRequest()
                .withSource(mailAddress)
                .withDestination(new Destination().withToAddresses(toEmail))
                .withMessage(new Message()
                        .withSubject(new Content(subject))
                        .withBody(new Body().withText(new Content(body)))
                );

        try {
            amazonSimpleEmailService.sendEmail(request);
        } catch (AmazonServiceException ex) {
            throw new RuntimeException("Email sending failed", ex);
        }
    }

    public void sendHtmlEmail(String to, String subject, String body) {
        SendEmailRequest request = new SendEmailRequest()
                .withSource(mailAddress)
                .withDestination(new Destination().withToAddresses(to))
                .withMessage(new Message()
                        .withSubject(new Content(subject))
                        .withBody(new Body().withHtml(new Content(body)))
                );
        try {
            amazonSimpleEmailService.sendEmail(request);
            log.info("Email sent successfully to {}", to);
        } catch (AmazonServiceException ex) {
            log.error("Error sending HTML email", ex);
            throw new RuntimeException("Email sending failed", ex);
        }
    }
}