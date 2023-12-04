package com.josephvanliew.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CurrentForecast(
        long dt, // Unix timestamp for the forecast time
        double temp, // Kalvin default
        @JsonProperty("feels_like")
        double feelsLike, // Feels like temperature
        int pressure, // Atmospheric pressure in hPa
        int humidity, // Humidity in percentage
        int clouds, // Cloudiness %
        int visibility, // Average visibility, metres. The maximum value of the visibility is 10 km
        @JsonProperty("wind_speed")
        double windSpeed, // Wind speed. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour.
        @JsonProperty("wind_deg")
        int windDeg, // Wind direction, degrees (meteorological)
        List<WeatherCondition> weather //weather condition, see HourlyForecast for hour snow and rain accumulation
) {

    public record WeatherCondition(
        int id,
        String main,
        String description,
        String icon
) {}

}
