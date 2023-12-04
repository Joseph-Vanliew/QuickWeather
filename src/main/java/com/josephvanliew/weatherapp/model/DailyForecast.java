package com.josephvanliew.weatherapp.model;

public record DailyForecast(
        long dt,
        Temperature temp
)
{
    public record Temperature(
            double day,
            double min,
            double max) {}
}
