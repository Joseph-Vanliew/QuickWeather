package com.josephvanliew.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CurrentForecast(
        long dt,
        double temp,
        @JsonProperty("feels_like")
        double feelsLike,
        int pressure,
        int humidity,
        int clouds,
        int visibility,
        @JsonProperty("wind_speed")
        double windSpeed,
        @JsonProperty("wind_deg")
        int windDeg,
        List<WeatherCondition> weather
) {

    public record WeatherCondition(
        int id,
        String main,
        String description,
        String icon
) {}

}
