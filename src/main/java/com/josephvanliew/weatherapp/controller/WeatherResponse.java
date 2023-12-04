package com.josephvanliew.weatherapp.controller;

import com.josephvanliew.weatherapp.model.CurrentForecast;
import com.josephvanliew.weatherapp.model.DailyForecast;
import com.josephvanliew.weatherapp.model.HourlyForecast;
import com.josephvanliew.weatherapp.model.WeatherAlert;

import java.util.List;

public class WeatherResponse {
    private CurrentForecast current;
    private List<HourlyForecast> hourly;
    private List<DailyForecast> daily;
    private List<WeatherAlert> alerts;

    private double lat;
    private double lon;

    public CurrentForecast getCurrent() {
        return current;
    }

    public void setCurrent(CurrentForecast current) {
        this.current = current;
    }

    public List<HourlyForecast> getHourly() {
        return hourly;
    }

    public void setHourly(List<HourlyForecast> hourly) {
        this.hourly = hourly;
    }

    public List<DailyForecast> getDaily() {
        return daily;
    }

    public void setDaily(List<DailyForecast> daily) {
        this.daily = daily;
    }

    public List<WeatherAlert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<WeatherAlert> alerts) {
        this.alerts = alerts;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
