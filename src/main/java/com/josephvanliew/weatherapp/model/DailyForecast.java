package com.josephvanliew.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DailyForecast(
        long dt,
        Temperature temp
)
{
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Temperature(
            double day,
            double min,
            double max) {}
}
