package org.gucardev.awssesmailsending;

import lombok.RequiredArgsConstructor;
import org.gucardev.awssesmailsending.dto.EmailRequest;
import org.gucardev.awssesmailsending.dto.HtmlEmailRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component
public class InitialService implements CommandLineRunner {

    private final EmailSenderService emailSenderService;
    private String receiver = "mail";

    @Override
    public void run(String... args) throws Exception {

        // Basic mail
        var emailRequest = new EmailRequest(receiver, "subj", "deneme maili body");
        emailSenderService.sendEmail(emailRequest.to(), emailRequest.subject(), emailRequest.body());

        // html template 1
        var htmlEmailRequest = new HtmlEmailRequest("subj", receiver, "deneme", "account-password");
        Map<String, Object> model = new HashMap<>();
        model.put("name", htmlEmailRequest.name());
        model.put("password", "123456");
        emailSenderService.htmlSend(htmlEmailRequest, model);

        // html template 2
        htmlEmailRequest = new HtmlEmailRequest("subj", receiver, "deneme", "activate-account");
        model = new HashMap<>();
        model.put("name", htmlEmailRequest.name());
        model.put("link", "http://localhost:8080/user/@" + htmlEmailRequest.name());
        emailSenderService.htmlSend(htmlEmailRequest, model);


        // html template with table
        htmlEmailRequest = new HtmlEmailRequest("subj-table", receiver, "deneme", "users");
        // Dynamic columns and rows
        List<List<String>> tableData = new ArrayList<>();
        // Adding headers - column1, column2
        tableData.add(Arrays.asList("İsim", "Soyisim"));
        // Adding rows
        tableData.add(Arrays.asList("Ali", "Özdemir"));
        tableData.add(Arrays.asList("Metin", "Özdemir"));
        tableData.add(Arrays.asList("Sezai", "Yanlız"));
        model = new HashMap<>();
        model.put("tableData", tableData);
        emailSenderService.htmlSend(htmlEmailRequest, model);
    }
}
