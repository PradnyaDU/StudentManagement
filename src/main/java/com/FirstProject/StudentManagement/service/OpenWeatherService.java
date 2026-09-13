package com.FirstProject.StudentManagement.service;

import com.FirstProject.StudentManagement.apiresponse.WeatherAPIResponse;
import com.FirstProject.StudentManagement.configclasses.AppCacheConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherService {

    @Autowired
    private AppCacheConfig appCacheConfig;

    @Autowired
    private RedisService redisService;

    //    @Value("${WEATHER_API_KEY}")
//    private String API_KEY;
    @Value("${WEATHER_API_URL}")
    private String BASE_URL;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherAPIResponse getWeatherData(double lat, double lon) throws Exception {
        String apiKey = appCacheConfig.get("APIKEY");
        WeatherAPIResponse weatherAPIResponse = redisService.getValue("weather:" + lat + ":" + lon, WeatherAPIResponse.class);
        if (weatherAPIResponse != null) {
            return weatherAPIResponse;
        } else {
            String url = BASE_URL + "?lat={lat}&lon={lon}&appid={apiKey}&units=metric";
            WeatherAPIResponse body = restTemplate.exchange(url, HttpMethod.GET, null, WeatherAPIResponse.class, lat, lon, apiKey).getBody();
            if (body != null) {
                redisService.setValue("weather:" + lat + ":" + lon, body, 600L);

            }
            return body;
        }
    }
}
