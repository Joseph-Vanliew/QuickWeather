package com.josephvanliew.weatherapp.controller;

import com.josephvanliew.weatherapp.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;
    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getCompleteWeatherData(@RequestParam String city) {
        try {
            return ResponseEntity.ok(weatherService.getCompleteWeatherData(city));
        } catch (Exception e) {
            logger.error("Error fetching weather data for city: " + city, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}


