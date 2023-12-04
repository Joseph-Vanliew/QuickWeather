package com.josephvanliew.weatherapp.customexceptions;

public class WeatherDataFetchException extends RuntimeException {
    public WeatherDataFetchException(String message) {
        super(message);
    }
}
