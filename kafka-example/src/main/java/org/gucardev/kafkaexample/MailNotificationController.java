package org.gucardev.kafkaexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mail")
public class MailNotificationController {

    @Autowired
    private MailNotificationProducerService mailNotificationProducerService;

    @PostMapping("/send")
    public String sendEmployeeNotification(@RequestBody Employee employee) {
        mailNotificationProducerService.sendEmployeeNotification(employee);
        return "Notification sent for employee: " + employee.getName();
    }

    @PostMapping("/send/bulk")
    public String sendEmployeesNotification(@RequestBody List<Employee> employees) {
        mailNotificationProducerService.sendEmployeesNotification(employees);
        return "Notifications sent for employees";
    }
}
