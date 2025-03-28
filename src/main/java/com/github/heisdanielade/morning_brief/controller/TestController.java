package com.github.heisdanielade.morning_brief.controller;

import com.github.heisdanielade.morning_brief.service.DailySummaryService;
import com.github.heisdanielade.morning_brief.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class TestController {

    private final EmailService emailService;
    private final DailySummaryService dailySummaryService;

    public TestController(EmailService emailService, DailySummaryService dailySummaryService) {
        this.emailService = emailService;
        this.dailySummaryService = dailySummaryService;
    }

    @GetMapping("/test-email")
    public String testEmail() {
        String emailContent = dailySummaryService.generateDailyBrief();
        String recipient = "danieladeofficial@gmail.com";
        String emailSubject = "🌞 Your Morning Brief";
        emailService.sendEmail(recipient, emailSubject, emailContent);
        return "Test email sent successfully!";
    }
}
