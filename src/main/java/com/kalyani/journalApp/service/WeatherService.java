package com.kalyani.journalApp.service;

import com.kalyani.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    private static final String apiKey="85c3ee86f3e39084b26a4456ad38516a";
    private static final String API_URL="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;


    public WeatherResponse getWeather(String city) {
        String finalApi=API_URL.replace("CITY",city).replace("API_KEY",apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
       return response.getBody();
    }

}
