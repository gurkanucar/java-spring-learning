package org.gucardev.awssesmailsending;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gucardev.awssesmailsending.dto.HtmlEmailRequest;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailSenderService {

    private final SesEmailSender sesEmailSender;
    private final SpringTemplateEngine templateEngine;

    public void sendEmail(String to, String subject, String body) {
        sesEmailSender.sendEmail(to, subject, body);
    }

    public void sendHtmlEmail(String to, String subject, String body) {
        sesEmailSender.sendHtmlEmail(to, subject, body);
    }

    public void htmlSend(HtmlEmailRequest HTMLrequest, Map<String, Object> model) {
        Context context = new Context();
        context.setVariables(model);

        String templateName = HTMLrequest.templateName();
        String to = HTMLrequest.to();
        String subject = HTMLrequest.subject();

        String processedHtml = templateEngine.process(templateName, context);
        log.debug("Processed HTML: {}", processedHtml);
        sendHtmlEmail(to, subject, processedHtml);
    }

}
