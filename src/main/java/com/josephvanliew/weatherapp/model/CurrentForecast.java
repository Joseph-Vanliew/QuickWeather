package com.josephvanliew.weatherapp.model;

import java.util.List;

public record CurrentForecast(
        long dt,
        double temp,
        double feelsLike,
        int pressure,
        int humidity,
        int clouds,
        int visibility,
        double windSpeed,
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
