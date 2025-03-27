package com.github.heisdanielade.morning_brief.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public WeatherService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public String getWeatherInfo(String city) {
        try {
            String url = String.format(BASE_URL, city, apiKey);
            String jsonResponse = restTemplate.getForObject(url, String.class);

            // Parse JSON response
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            String cityName = rootNode.get("name").asText();
            double temperature = rootNode.get("main").get("temp").asDouble();
            String description = rootNode.get("weather").get(0).get("description").asText();

            return "City: " + cityName + "\nTemperature: " + temperature + "°C\nCondition: " + description;
        }catch (HttpClientErrorException e) {
            return "Error fetching weather data: " + e.getStatusCode() + " - " + e.getResponseBodyAsString();
        } catch (JsonProcessingException e) {
            return "Error parsing weather data.";
        }

    }

}
