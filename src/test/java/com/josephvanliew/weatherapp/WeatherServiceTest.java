package com.josephvanliew.weatherapp;

import com.josephvanliew.weatherapp.client.GeocodingClient;
import com.josephvanliew.weatherapp.client.WeatherClient;
import com.josephvanliew.weatherapp.controller.WeatherResponse;
import com.josephvanliew.weatherapp.model.Coordinates;
import com.josephvanliew.weatherapp.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class WeatherServiceTest {

    @Mock
    private GeocodingClient geocodingClient;

    @Mock
    private WeatherClient weatherClient;

    @InjectMocks
    private WeatherService weatherService;

    @BeforeEach
    void setUp() {

        when(geocodingClient.getCoordinatesForCity("TestCity"))
                .thenReturn(Optional.of(new Coordinates(10.0, 20.0)));

        when(weatherClient.getWeatherData(10.0, 20.0, "imperial"))
                .thenReturn(new WeatherResponse());
    }

    @Test
    void getCompleteWeatherDataForValidCity() {

        WeatherResponse response = weatherService.getCompleteWeatherData("TestCity");

        assertNotNull(response);
        verify(geocodingClient).getCoordinatesForCity("TestCity");
        verify(weatherClient).getWeatherData(10.0, 20.0, "imperial");
    }

    @Test
    void getCompleteWeatherDataForInvalidCity() {

        when(geocodingClient.getCoordinatesForCity("InvalidCity")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            weatherService.getCompleteWeatherData("InvalidCity");
        });
        assertEquals("Unable to find weather data for the given city.", exception.getMessage());
    }
}
