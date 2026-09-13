package com.FirstProject.StudentManagement.apiresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class WeatherAPIResponse {

    @JsonProperty("coord")
    public Coord coord;

    @JsonProperty("weather")
    public ArrayList<Weather> weather;

    @JsonProperty("base")
    public String base;

    @JsonProperty("main")
    public Main main;

    @JsonProperty("visibility")
    public int visibility;

    @JsonProperty("wind")
    public Wind wind;

    @JsonProperty("clouds")
    public Clouds clouds;

    @JsonProperty("dt")
    public int dt;

    @JsonProperty("sys")
    public Sys sys;

    @JsonProperty("timezone")
    public int timezone;

    @JsonProperty("id")
    public int id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("cod")
    public int cod;

    @Data
    public static class Clouds {

        @JsonProperty("all")
        public int all;
    }

    @Data
    public static class Coord {

        @JsonProperty("lon")
        public double lon;

        @JsonProperty("lat")
        public double lat;
    }

    @Data
    public static class Main {

        @JsonProperty("temp")
        public double temp;

        @JsonProperty("feels_like")
        public double feelsLike;

        @JsonProperty("temp_min")
        public double tempMin;

        @JsonProperty("temp_max")
        public double tempMax;

        @JsonProperty("pressure")
        public int pressure;

        @JsonProperty("humidity")
        public int humidity;

        @JsonProperty("sea_level")
        public int seaLevel;

        @JsonProperty("grnd_level")
        public int grndLevel;
    }

    @Data
    public static class Sys {

        @JsonProperty("country")
        public String country;

        @JsonProperty("sunrise")
        public int sunrise;

        @JsonProperty("sunset")
        public int sunset;
    }

    @Data
    public static class Weather {

        @JsonProperty("id")
        public int id;

        @JsonProperty("main")
        public String main;

        @JsonProperty("description")
        public String description;

        @JsonProperty("icon")
        public String icon;
    }

    @Data
    public static class Wind {

        @JsonProperty("speed")
        public double speed;

        @JsonProperty("deg")
        public int deg;

        @JsonProperty("gust")
        public double gust;
    }
}