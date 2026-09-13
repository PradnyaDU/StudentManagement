package com.FirstProject.StudentManagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {
    private final EmailService emailService;

    @KafkaListener(
            topics = "student-created-topic",
            groupId = "email-service"
    )
    public void consumeStudentCreatedEvent(String email) {

        System.out.println("Received from Kafka: " + email);

        emailService.sendEmail(
                email,
                "Welcome",
                "Your student account has been created successfully."
        );
    }
}
