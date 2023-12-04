package com.josephvanliew.weatherapp.model;

import java.util.List;

public record HourlyForecast(
        long dt, // Unix timestamp for the forecast time
        double temp, // Temperature
        double feelsLike, // Feels like temperature
        int pressure, // Atmospheric pressure in hPa
        int humidity, // Humidity in percentage
        double dewPoint, // Dew point temperature
        double uvi, // UV index
        int clouds, // Cloudiness in percentage
        int visibility, // Average visibility in meters
        double windSpeed, // Wind speed
        int windDeg, // Wind direction in degrees
        double windGust, // Wind gust speed
        List<WeatherCondition> weather, // Weather conditions
        double pop // Probability of precipitation
) {

    public record WeatherCondition(
            int id, // Weather condition id
            String main, // Group of weather parameters (Rain, Snow, Extreme, etc.)
            String description, // Weather condition within the group
            String icon // Weather icon id
    ) {
    }
}
