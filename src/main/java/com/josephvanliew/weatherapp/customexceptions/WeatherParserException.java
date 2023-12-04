package com.josephvanliew.weatherapp.customexceptions;

public class WeatherParserException extends RuntimeException {
    public WeatherParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
