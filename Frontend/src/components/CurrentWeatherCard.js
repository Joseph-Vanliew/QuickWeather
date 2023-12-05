import React from 'react';

function CurrentWeatherCard({ current }) {
    return (
        <div className={"weather-card current-weather"}>
            <h2>Current Weather</h2>
            <p>Temperature: {Math.round(current?.temp)}°F</p>
            <p>Feels Like: {Math.round(current?.feels_like)}°F</p>
            <p>Humidity: {current?.humidity}%</p>
            <p>Wind: {Math.round(current?.wind_speed)} mph</p>
            {/* Will add additional when tested */}

            <h2>Weather Condition</h2>
            {current?.weather.map((condition, index) => (
                <div key={index}>
                    <p>Main: {condition.main}</p>
                    <p>Description: {condition.description}</p>
                    {/* similar to comment above */}
                </div>
            ))}
        </div>
    );
}
export default CurrentWeatherCard;