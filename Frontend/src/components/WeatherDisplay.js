import React, { useState } from 'react';
import CurrentWeatherCard from './CurrentWeatherCard';
import HourlyForecastCard from './HourlyForecastCard';
import DailyForecastCard from './DailyForecastCard';
import WeatherAlertsCard from './WeatherAlertsCard';

import '../css.styles/WeatherDisplay.css'
function WeatherDisplay() {
    const [city, setCity] = useState('');
    const [weatherData, setWeatherData] = useState(null);

    const fetchWeatherData = () => {
        if (!city) return; // Prevent fetching if city is not set

        fetch(`/weather/all?city=${encodeURIComponent(city)}`)
            .then(response => response.json())
            .then(data => setWeatherData(data))
            .catch(error => console.error('Error fetching weather data:', error));
    };

    const handleCityChange = (event) => {
        setCity(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        fetchWeatherData();
    };

    return (
        <div>
            {/* Form for entering city */}
            <form onSubmit={handleSubmit}>
                <label htmlFor="city-input">Enter City:</label>
                <input
                    id="city-input"
                    type="text"
                    value={city}
                    onChange={handleCityChange}
                />
                <button type="submit">Get Weather</button>
            </form>

            {/* Weather display cards, only shown if weatherData is available */}
            {weatherData && (
                <div className={"weather-display"}>
                    <CurrentWeatherCard current={weatherData.current} />
                    <HourlyForecastCard hourly={weatherData.hourly} />
                    <DailyForecastCard daily={weatherData.daily} />
                    <WeatherAlertsCard alerts={weatherData.alerts} />
                </div>
            )}
        </div>
    );
}

export default WeatherDisplay;
