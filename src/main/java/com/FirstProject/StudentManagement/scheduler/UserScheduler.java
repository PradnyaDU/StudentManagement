package com.FirstProject.StudentManagement.scheduler;

import com.FirstProject.StudentManagement.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserScheduler {
    @Autowired
    private EmailService emailService;
    private int emailCount = 0;

    @Scheduled(cron = "0 */10 0 14 9 *")
    public void sendEmailBySchedular() {
        emailCount++;
        LocalDateTime nextRunTime = LocalDateTime.now().plusMinutes(10);
        System.out.println("Next email will be sent at: " + nextRunTime);
        System.out.println("Sending email #" + emailCount);
        try {
            List<String> recipients = List.of(
                    "bibaveharshal@gmail.com",
                    "deshpandepradnya18@gmail.com"
            );
            String subject = "Scheduled Email : " + emailCount;
            String body = "Nothing hurts more than feeling alone while loving someone who was supposed to make you feel loved: " + emailCount;
            for (String to : recipients) {
                emailService.sendEmail(to, subject, body);
            }
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }
    }
}

