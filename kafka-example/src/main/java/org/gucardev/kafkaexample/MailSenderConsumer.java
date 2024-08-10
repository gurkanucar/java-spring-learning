package org.gucardev.kafkaexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class MailSenderConsumer {

    @KafkaListener(topics = "employee-mail-notifications", groupId = "mail-sending-service-group")
    public void listenSingleEmployee(Employee employee, Acknowledgment acknowledgment) {
        log.info("Received message: {}", employee.toString());
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "employee-list-mail-notifications", groupId = "mail-sending-service-group")
    public void listenMultipleEmployees(List<Employee> employees, Acknowledgment acknowledgment) {
        log.info("Received message list: {}", Arrays.toString(employees.toArray()));
        acknowledgment.acknowledge();
    }
}
