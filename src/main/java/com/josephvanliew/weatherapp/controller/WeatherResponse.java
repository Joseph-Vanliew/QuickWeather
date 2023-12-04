package com.josephvanliew.weatherapp.controller;

import com.josephvanliew.weatherapp.model.CurrentForecast;
import com.josephvanliew.weatherapp.model.DailyForecast;
import com.josephvanliew.weatherapp.model.HourlyForecast;
import com.josephvanliew.weatherapp.model.WeatherAlert;

import java.util.List;

public class WeatherResponse {
    private CurrentForecast current;
    private HourlyForecast hourly;
    private DailyForecast daily;
    private WeatherAlert alerts;

    public CurrentForecast getCurrent() {
        return current;
    }

    public void setCurrent(CurrentForecast current) {
        this.current = current;
    }

    public HourlyForecast getHourly() {
        return hourly;
    }

    public void setHourly(HourlyForecast hourly) {
        this.hourly = hourly;
    }

    public DailyForecast getDaily() {
        return daily;
    }

    public void setDaily(DailyForecast daily) {
        this.daily = daily;
    }

    public WeatherAlert getAlerts() {
        return alerts;
    }

    public void setAlerts(WeatherAlert alerts) {
        this.alerts = alerts;
    }
}
