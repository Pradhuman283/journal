package com.algoknight.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import api_response.WeatherApiResponse;

@Component
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    private final String API_KEY = "60217953f2d547d7ae272301262103";
    private final String BASE_URL = "http://api.weatherapi.com/v1/current.json?key=";

    public WeatherApiResponse getWeather(String city) {
        String url = BASE_URL + API_KEY + "&q=" + city + "&aqi=no";
        return restTemplate.getForObject(url, WeatherApiResponse.class);
    }
}