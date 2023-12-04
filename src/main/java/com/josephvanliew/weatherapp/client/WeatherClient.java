package com.josephvanliew.weatherapp.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.josephvanliew.weatherapp.controller.WeatherResponse;
import com.josephvanliew.weatherapp.customexceptions.WeatherDataFetchException;
import com.josephvanliew.weatherapp.customexceptions.WeatherParserException;
import com.josephvanliew.weatherapp.model.CurrentForecast;
import com.josephvanliew.weatherapp.model.DailyForecast;
import com.josephvanliew.weatherapp.model.HourlyForecast;
import com.josephvanliew.weatherapp.model.WeatherAlert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Component
public class WeatherClient {

    @Value("${WEATHER_API_KEY}")
    private String API_KEY;
    @Value("${WEATHER_BASE_URL}")
    private String BASE_URL;

    private static final Logger logger = LoggerFactory.getLogger(WeatherClient.class);

    public WeatherResponse getWeatherData(double lat, double lon) throws WeatherDataFetchException {
        HttpClient client = HttpClient.newHttpClient();
        String url = BASE_URL + "?lat=" + lat + "&lon=" + lon + "&appid=" + API_KEY;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return parseWeatherResponse(response.body());
            } else {
                logger.error("Failed to fetch the weather data for the given Lat: " + lat + ", and longitude: " + lon + ", status code: " + response.statusCode() + ", response body: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            logger.error("Error occurred while calling Weather API", e);
        }
        throw new WeatherDataFetchException("Failed to fetch Weather data");
    }

    private WeatherResponse parseWeatherResponse(String responseBody) throws WeatherParserException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);

            WeatherResponse weatherResponse = new WeatherResponse();

            // Parsing current weather
            JsonNode currentNode = rootNode.path("current");
            if(currentNode.isArray()) {
                CurrentForecast currentForecast = objectMapper.readValue(
                        currentNode.toString(),
                        new TypeReference<>() {}
                );
                weatherResponse.setCurrent(currentForecast);
            }

            // Parsing hourly forecasts
            JsonNode hourlyNode = rootNode.path("hourly");
            if (hourlyNode.isArray()) {
                HourlyForecast hourlyForecasts = objectMapper.readValue(
                        hourlyNode.toString(),
                        new TypeReference<>() {}
                );
                weatherResponse.setHourly(hourlyForecasts);
            }

            // Parsing daily forecasts
            JsonNode dailyNode = rootNode.path("daily");
            if (dailyNode.isArray()) {
                DailyForecast dailyForecasts = objectMapper.readValue(
                        dailyNode.toString(),
                        new TypeReference<>() {}
                );
                weatherResponse.setDaily(dailyForecasts);
            }

            // Parsing weather alerts
            JsonNode alertsNode = rootNode.path("alerts");
            if (alertsNode.isArray()) {
                WeatherAlert weatherAlerts = objectMapper.readValue(
                        alertsNode.toString(),
                        new TypeReference<>() {}
                );
                weatherResponse.setAlerts(weatherAlerts);
            }

            return weatherResponse;
        } catch (JsonProcessingException e) {
            logger.error("Error occurred while parsing Weather API response: ", e);
            throw new WeatherParserException("Failed to parse weather data: ", e);
        }
    }
}
