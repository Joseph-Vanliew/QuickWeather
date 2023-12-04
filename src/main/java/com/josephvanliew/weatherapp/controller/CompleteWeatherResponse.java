package com.josephvanliew.weatherapp.controller;

import com.josephvanliew.weatherapp.model.CurrentForecast;
import com.josephvanliew.weatherapp.model.DailyForecast;
import com.josephvanliew.weatherapp.model.HourlyForecast;
import com.josephvanliew.weatherapp.model.WeatherAlert;

import java.util.List;

public record CompleteWeatherResponse(
        List<CurrentForecast> currentForecast,
        List<HourlyForecast> hourlyForecasts,
        List<DailyForecast> dailyForecasts,
        List<WeatherAlert> weatherAlerts) {}

