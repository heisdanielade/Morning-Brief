package com.github.heisdanielade.morning_brief.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class DailySummaryService {

    private final WeatherService weatherService;

    public DailySummaryService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public String getEmailTemplate(String city, String weather) {
        try {
            String template = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/email-template.html")));
            return template.replace("{{city}}", city).replace("{{weather}}", weather);
        } catch (IOException e) {
            return "Error loading email template." + e.getMessage();
        }
    }

    public String generateDailyBrief() {
        String city = "Zielona Góra";
        String weatherInfo = weatherService.getWeatherInfo(city);
        return getEmailTemplate(city, weatherInfo);


    }
}
