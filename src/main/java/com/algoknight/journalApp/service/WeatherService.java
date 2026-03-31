package com.algoknight.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.algoknight.journalApp.api_response.WeatherApiResponse;

import org.springframework.beans.factory.annotation.Value;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    @Value("${weatherapi.key}")
    private String API_KEY;
    private final String BASE_URL = "http://api.weatherapi.com/v1/current.json?key=";

    public WeatherApiResponse getWeather(String city) {
        try {
            WeatherApiResponse cachedWeather = (WeatherApiResponse) redisService.getValue(city);
            if (cachedWeather != null) {
                log.info("Successfully retrieved weather from Redis for city: {}", city);
                return cachedWeather;
            }
        } catch (Exception e) {
            log.error("Error getting weather from Redis for city {}: {}", city, e.getMessage());
        }

        String url = BASE_URL + API_KEY + "&q=" + city + "&aqi=no";
        WeatherApiResponse weatherApiResponse = restTemplate.getForObject(url, WeatherApiResponse.class);

        if (weatherApiResponse != null) {
            try {
                redisService.setValue(city, weatherApiResponse, 10, TimeUnit.MINUTES);
                log.info("Successfully cached weather for city: {}", city);
            } catch (Exception e) {
                log.error("Error setting weather in Redis for city {}: {}", city, e.getMessage());
            }
        }
        return weatherApiResponse;
    }
}