package com.github.heisdanielade.morning_brief.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTaskService {

    private final EmailService emailService;
    private final DailySummaryService dailySummaryService;

    public ScheduleTaskService(EmailService emailService, DailySummaryService dailySummaryService) {
        this.emailService = emailService;
        this.dailySummaryService = dailySummaryService;
    }

    @Scheduled(cron = "0 0 7 * * ?") // Every day at 7 AM
    public void sendDailyEmail() {
        String emailContent = dailySummaryService.generateDailyBrief();
        String recipient = "test@gmail.com";
        String emailSubject = "🌞 Your Morning Brief";
        emailService.sendEmail(recipient, emailSubject, emailContent);
    }
}
