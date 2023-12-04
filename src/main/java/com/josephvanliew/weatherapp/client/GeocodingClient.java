package com.josephvanliew.weatherapp.client;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.josephvanliew.weatherapp.customexceptions.FailedToFetchCityNameException;
import com.josephvanliew.weatherapp.model.Coordinates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
public class GeocodingClient {

    @Value("${WEATHER_API_KEY}")
    private String API_KEY;
    @Value("${GEOCODING_BASE_URL}")
    private String BASE_URL;

    private static final Logger logger = LoggerFactory.getLogger(GeocodingClient.class);


    private final ObjectMapper objectMapper = new ObjectMapper();

    public Optional<Coordinates> getCoordinatesForCity(String city) throws FailedToFetchCityNameException {
        HttpClient client = HttpClient.newHttpClient();
        String url = String.format("%s?q=%s&limit=1&appid=%s",
                BASE_URL,
                URLEncoder.encode(city, StandardCharsets.UTF_8),
                API_KEY);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return parseCoordinates(response.body());
            } else {
                logger.error("Failed to fetch coordinates for city: " + city + ". Status code: " + response.statusCode() + ", Response: " + response.body());
                throw new FailedToFetchCityNameException("API call failed with status code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            throw new FailedToFetchCityNameException("Could not find the City from the given coordinates");
        }
    }

    private Optional<Coordinates> parseCoordinates(String responseBody) {
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            if (rootNode.isArray() && !rootNode.isEmpty()) {

                JsonNode firstElement = rootNode.get(0);
                double lat = firstElement.path("lat").asDouble();
                double lon = firstElement.path("lon").asDouble();
                return Optional.of(new Coordinates(lat, lon));
            }
        } catch (IOException e) {
            logger.error("Error parsing coordinates from response body: " + responseBody);
        }
        return Optional.empty();
    }
}
