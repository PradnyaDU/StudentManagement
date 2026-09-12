package com.FirstProject.StudentManagement.Service;

import com.FirstProject.StudentManagement.apiresponse.WeatherAPIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherService {
    private static final String API_KEY = "311fc328d71a263c6091c7656e6e797c"; // Replace with your
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}";

    @Autowired
    private RestTemplate restTemplate;
    public WeatherAPIResponse getWeatherData(double lat, double lon) {
        String url = BASE_URL.replace("{lat}", String.valueOf(lat)).replace("{lon}", String.valueOf(lon)).replace("{API key}", API_KEY);
        return restTemplate.exchange(url, HttpMethod.GET, null, WeatherAPIResponse.class).getBody();
    }


}
