package com.josephvanliew.weatherapp.model;

public record WeatherAlert(
        String senderName,
        String event,
        long start,
        long end,
        String description
) {}

