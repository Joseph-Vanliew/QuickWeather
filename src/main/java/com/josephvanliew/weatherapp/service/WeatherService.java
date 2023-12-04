package com.josephvanliew.weatherapp.service;

import com.josephvanliew.weatherapp.client.GeocodingClient;
import com.josephvanliew.weatherapp.client.WeatherClient;
import com.josephvanliew.weatherapp.controller.WeatherResponse;
import com.josephvanliew.weatherapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {

    private final GeocodingClient geocodingClient;
    private final WeatherClient weatherAPIClient;

    @Autowired
    public WeatherService(GeocodingClient geocodingClient, WeatherClient weatherAPIClient) {
        this.geocodingClient = geocodingClient;
        this.weatherAPIClient = weatherAPIClient;
    }

    private Coordinates getCoordinatesForCity(String city) {
        Optional<Coordinates> coordinates = geocodingClient.getCoordinatesForCity(city);
        if (coordinates.isPresent()) {
            return coordinates.get();
        } else {
            throw new RuntimeException("Unable to find weather data for the given city.");
        }
    }

    public WeatherResponse getCompleteWeatherData(String city) {
        Coordinates coordinates = getCoordinatesForCity(city);
        String metric = "metric";
        return weatherAPIClient.getWeatherData(coordinates.lat(), coordinates.lon(), metric);
    }
}

