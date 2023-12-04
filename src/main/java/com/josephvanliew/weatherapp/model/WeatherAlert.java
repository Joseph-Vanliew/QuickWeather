package com.josephvanliew.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record WeatherAlert(
        @JsonProperty("sender_name")
        String senderName,
        String event,
        long start,
        long end,
        String description,
        @JsonProperty("tags")
        List<String> tags
) {
}

