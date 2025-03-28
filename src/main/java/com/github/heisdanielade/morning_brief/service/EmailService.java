package com.github.heisdanielade.morning_brief.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender emailSender;
    private final DailySummaryService dailySummaryService;

    public EmailService(JavaMailSender emailSender, DailySummaryService dailySummaryService) {
        this.emailSender = emailSender;
        this.dailySummaryService = dailySummaryService;
    }

    public void sendDailyBriefEmail(String recipientEmail) {
        String subject = "Your Morning Brief";
        String content = dailySummaryService.generateDailyBrief();

        sendEmail(recipientEmail, subject, content);
    }

    public void sendEmail(String to, String subject, String body) {
        try{
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            emailSender.send(message);
            System.out.println("\n(i) Email sent successfully to " + to);
        } catch (MessagingException e){
            throw new RuntimeException("\n(e) Failed to send email", e);
        }
    }
}