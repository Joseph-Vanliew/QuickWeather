package com.josephvanliew.weatherapp.controller;

import com.josephvanliew.weatherapp.model.CurrentForecast;
import com.josephvanliew.weatherapp.model.DailyForecast;
import com.josephvanliew.weatherapp.model.HourlyForecast;
import com.josephvanliew.weatherapp.model.WeatherAlert;
import com.josephvanliew.weatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getCompleteWeatherData(@RequestParam String city) {
        try {
            List<CurrentForecast> currentForecast = weatherService.getCurrentWeather(city);
            List<HourlyForecast> hourlyForecast = weatherService.getHourlyForecast(city);
            List<DailyForecast> dailyForecast = weatherService.getDailyForecast(city);
            List<WeatherAlert> weatherAlerts = weatherService.getWeatherAlerts(city);

            CompleteWeatherResponse completeWeatherResponse = new CompleteWeatherResponse(
                    currentForecast, hourlyForecast, dailyForecast, weatherAlerts
            );

            return ResponseEntity.ok(completeWeatherResponse);
        } catch (Exception e) {
            // Handle exceptions (e.g., city not found, API errors)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}


