import React, { useState, useEffect } from 'react';
import CurrentWeatherCard from './CurrentWeatherCard';
import HourlyForecastCard from './HourlyForecastCard';
import DailyForecastCard from './DailyForecastCard';
import WeatherAlertsCard from './WeatherAlertsCard';
import '../css.styles/WeatherDisplay.css';

function WeatherDisplay({ initialCity }) {
    const [city, setCity] = useState(initialCity); // Use a new state variable for the current city
    const [inputCity, setInputCity] = useState(''); // Reset input field when navigating to this component
    const [weatherData, setWeatherData] = useState(null);

    useEffect(() => {
        if (city) {
            fetchWeatherData(city);
        }
    }, [city]); // Fetch weather data when city changes

    const fetchWeatherData = async (city) => {
        try {
            const response = await fetch(`/weather/all?city=${encodeURIComponent(city)}`);
            const data = await response.json();
            setWeatherData(data);
        } catch (error) {
            console.error('Error fetching weather data:', error);
        }
    };

    const handleCityChange = (event) => {
        setInputCity(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        setCity(inputCity);
        setInputCity('');
    };

    return (
        <div className={"weather-display"}>
            <form onSubmit={handleSubmit}>
                <label htmlFor="city-input">City:</label>
                <input
                    id="city-input"
                    type="text"
                    value={inputCity}
                    onChange={handleCityChange}
                    placeholder="Enter new city"
                />
                <button type="submit">Get Weather</button>
            </form>

            {weatherData ? (
                <div>
                    <CurrentWeatherCard current={weatherData.current} city={city} />
                    <HourlyForecastCard hourly={weatherData.hourly} />
                    <DailyForecastCard daily={weatherData.daily} />
                    <WeatherAlertsCard alerts={weatherData.alerts} />
                </div>
            ) : (
                <p>Loading weather data...</p>
            )}
        </div>
    );
}

export default WeatherDisplay;
