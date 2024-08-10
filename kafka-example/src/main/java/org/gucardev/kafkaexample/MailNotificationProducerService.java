package org.gucardev.kafkaexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailNotificationProducerService {

    @Value("${topics.single-employee-topic}")
    private String SINGLE_EMPLOYEE_TOPIC;
    @Value("${topics.multiple-employees-topic}")
    private String MULTIPLE_EMPLOYEES_TOPIC;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendEmployeeNotification(Employee employee) {
        kafkaTemplate.send(SINGLE_EMPLOYEE_TOPIC, employee.getId().toString(), employee);
    }

    public void sendEmployeesNotification(List<Employee> employees) {
        kafkaTemplate.send(MULTIPLE_EMPLOYEES_TOPIC, "employees", employees);
    }
}
