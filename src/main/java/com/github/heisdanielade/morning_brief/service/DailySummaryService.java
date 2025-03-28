package com.github.heisdanielade.morning_brief.service;

import org.springframework.stereotype.Service;

@Service
public class DailySummaryService {

    private final WeatherService weatherService;

    public DailySummaryService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public String generateDailyBrief() {
        String city = "Zielona Góra";
        String weatherInfo = weatherService.getWeatherInfo(city);

        return """
        <html>
        <head>
            <style>
                body { font-family: 'Arial', sans-serif; background-color: #f4f4f4; padding: 20px; }
                .container { max-width: 600px; margin: auto; background: white; padding: 20px; border-radius: 12px; 
                             box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); }
                .header { text-align: center; font-size: 22px; color: #2d89ef; font-weight: bold; }
                .subtext { text-align: center; font-size: 16px; color: #555; margin-bottom: 20px; }
                .section { background: #f9f9f9; padding: 15px; border-radius: 8px; margin-bottom: 10px; }
                .section-title { font-size: 18px; color: #333; font-weight: bold; }
                .section-content { font-size: 16px; color: #666; margin-top: 5px; }
                .footer { text-align: center; font-size: 14px; color: #999; margin-top: 20px; }
            </style>
        </head>
        <body>
            <div class="container">
                <div class="header">🌞 Good Morning!</div>
                <div class="subtext">Here’s your daily briefing for %s:</div>
                <div class="section">
                    <div class="section-title">📍 Weather Update</div>
                    <div class="section-content">%s</div>
                </div>
                <div class="footer">Have a fantastic day! 😊</div>
            </div>
        </body>
        </html>
        """.formatted(city, weatherInfo);
    }
}
