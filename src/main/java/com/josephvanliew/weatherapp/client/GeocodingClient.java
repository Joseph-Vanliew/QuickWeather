package com.josephvanliew.weatherapp.client;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.josephvanliew.weatherapp.model.Coordinates;
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

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Optional<Coordinates> getCoordinatesForCity(String city) {
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
                //handle later
            }
        } catch (IOException | InterruptedException e) {
            // Log the exception and/or handle it
        }
        return Optional.empty();
    }

    private Optional<Coordinates> parseCoordinates(String responseBody) {
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            if (rootNode.isArray() && rootNode.size() > 0) {
                // Assuming you are taking the first match for simplicity
                JsonNode firstElement = rootNode.get(0);
                double lat = firstElement.path("lat").asDouble();
                double lon = firstElement.path("lon").asDouble();
                return Optional.of(new Coordinates(lat, lon));
            }
        } catch (IOException e) {
            // Log error or handle parsing exception
        }
        return Optional.empty();
    }
}
