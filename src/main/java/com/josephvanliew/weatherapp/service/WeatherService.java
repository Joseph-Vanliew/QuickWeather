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
    private final WeatherClient weatherClient;

    @Autowired
    public WeatherService(GeocodingClient geocodingClient, WeatherClient weatherClient) {
        this.geocodingClient = geocodingClient;
        this.weatherClient = weatherClient;
    }

    public List<CurrentForecast> getCurrentWeather(String city) {
        Coordinates coordinates = getCoordinatesForCity(city);
        WeatherResponse weatherResponse = weatherClient.getWeatherData(coordinates.lat(), coordinates.lon());
        return weatherResponse.getCurrent();
    }

    public List<HourlyForecast> getHourlyForecast(String city) {
        Coordinates coordinates = getCoordinatesForCity(city);
        WeatherResponse weatherResponse = weatherClient.getWeatherData(coordinates.lat(), coordinates.lon());
        return weatherResponse.getHourly();
    }

    public List<DailyForecast> getDailyForecast(String city) {
        Coordinates coordinates = getCoordinatesForCity(city);
        WeatherResponse weatherResponse = weatherClient.getWeatherData(coordinates.lat(), coordinates.lon());
        return weatherResponse.getDaily();
    }

    public List<WeatherAlert> getWeatherAlerts(String city) {
        Coordinates coordinates = getCoordinatesForCity(city);
        WeatherResponse weatherResponse = weatherClient.getWeatherData(coordinates.lat(), coordinates.lon());
        return weatherResponse.getAlerts();
    }

    private Coordinates getCoordinatesForCity(String city) {
        Optional<Coordinates> coordinates = geocodingClient.getCoordinatesForCity(city);
        if (coordinates.isPresent()) {
            return coordinates.get();
        } else {
            throw new RuntimeException("Unable to find weather for the given city.");
        }
    }
}

