package com.FirstProject.StudentManagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendStudentCreatedEvent(String email) {

        System.out.println("Sending to Kafka: " + email);

        kafkaTemplate.send(
                "student-created-topic",
                email
        );
    }
}
