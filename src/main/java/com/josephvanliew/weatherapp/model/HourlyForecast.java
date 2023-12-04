package com.josephvanliew.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;

public record HourlyForecast(
        long dt, // Unix timestamp for the forecast time
        double temp, // Temperature
        @JsonProperty("feels_like")
        double feelsLike, // Feels like temperature
        int pressure, // Atmospheric pressure in hPa
        int humidity, // Humidity in percentage
        @JsonProperty("dew_point")
        double dewPoint, // Dew point temperature
        double uvi, // UV index
        int clouds, // Cloudiness in percentage
        int visibility, // Average visibility in meters
        @JsonProperty("wind_speed")
        double windSpeed, // Wind speed
        @JsonProperty("wind_deg")
        int windDeg, // Wind direction in degrees
        @JsonProperty("wind_gust")
        double windGust, // Wind gust speed
        List<WeatherCondition> weather, // Weather conditions
        double pop, // Probability of precipitation
        Rain rain
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record WeatherCondition(
            int id, // Weather condition id
            @JsonProperty("main")
            String main, // Group of weather parameters (Rain, Snow, Extreme, etc.)
            String description, // Weather condition within the group
            String icon // Weather icon id
    ) {}

    public record Rain(
            @JsonProperty("1h")
            Optional<Double> amountInLastHour // Amount of rain in the last hour
    ) {}


}
