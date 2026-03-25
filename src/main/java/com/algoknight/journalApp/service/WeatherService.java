package com.algoknight.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.algoknight.journalApp.api_response.WeatherApiResponse;

import org.springframework.beans.factory.annotation.Value;

@Component
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weatherapi.key}")
    private String API_KEY;
    private final String BASE_URL = "http://api.weatherapi.com/v1/current.json?key=";

    public WeatherApiResponse getWeather(String city) {
        String url = BASE_URL + API_KEY + "&q=" + city + "&aqi=no";
        return restTemplate.getForObject(url, WeatherApiResponse.class);
    }
}