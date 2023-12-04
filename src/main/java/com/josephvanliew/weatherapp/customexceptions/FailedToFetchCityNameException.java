package com.josephvanliew.weatherapp.customexceptions;

public class FailedToFetchCityNameException extends RuntimeException {
    public FailedToFetchCityNameException(String message) {
        super(message);
    }

}
