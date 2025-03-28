package com.github.heisdanielade.morning_brief.controller;


import com.github.heisdanielade.morning_brief.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class TestController {

    private final EmailService emailService;

    public TestController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/test-email")
    public String testEmail() {
        String recipient = "danieladeofficial@gmail.com";
        emailService.sendDailyBriefEmail(recipient);
        return "Test email sent successfully!";
    }
}
