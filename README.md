# Quick Weather

This is a simple weather app for educational purposes.

## Author(s)
- [Joseph Vanliew](https://github.com/Joseph-Vanliew)
## API Reference
This app uses the OpenWeather(OW) [One Call 3.0 API](https://openweathermap.org/api/one-call-3). Like the API we only provide one endpoint to it. All the data is coalesced into one CompleteWeatherResponse object which is exposed to our frontend with all relevant data one might need in a simple weather application.

This app also uses OW's [Geocoding API 1.0](https://openweathermap.org/api/geocoding-api) to grab the longitude and latitude of a given city by the user. Longitude and Latitude are required for the One Call API to function properly.

Current implementation:

#### Get all weather data

```http
  GET /weather/all
```
###### base_url: https://api.openweathermap.org/data/3.0/onecall

###### api_key: your_api_key

| Parameter | Type     | Description                          |
| :-------- | :------- |:-------------------------------------|
| `city` | `string` | **Required**. base_url, your_api_key |

## Future Updates
- Weather Graphs.

- Weather Map for given locaiton in a grid of a tbd size.

- Beefed up UI for a better user experience
