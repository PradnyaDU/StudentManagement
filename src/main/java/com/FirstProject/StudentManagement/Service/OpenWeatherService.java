package com.FirstProject.StudentManagement.Service;

import com.FirstProject.StudentManagement.apiresponse.WeatherAPIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherService {

    @Value("${WEATHER_API_KEY}")
    private String API_KEY;
    @Value("${WEATHER_API_URL}")
    private String BASE_URL;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherAPIResponse getWeatherData(double lat, double lon) {

        String url = BASE_URL + "?lat={lat}&lon={lon}&appid={apiKey}&units=metric";
        return restTemplate.exchange(url, HttpMethod.GET, null,WeatherAPIResponse.class, lat, lon, API_KEY).getBody();
    }
}
