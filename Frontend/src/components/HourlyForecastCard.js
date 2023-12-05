import React from 'react';

function HourlyForecastCard({ hourly }) {
    // Slice the hourly data to only show the first 8 hours
    const limitedHourlyData = hourly?.slice(0, 8);

    return (
        <div className={"weather-card hourly-forecast"}>
            <h2>Hourly (Next 8 Hours)</h2>
            {limitedHourlyData?.map((forecast, index) => (
                <div key={index}>
                    <p>Time: {new Date(forecast.dt * 1000).toLocaleTimeString()}</p>
                    <p>Temperature: {Math.round(forecast.temp)}°F</p>
                    <p>Humidity: {forecast.humidity}%</p>
                    <p>Precipitation Chance: {forecast.pop * 100}%</p>
                    {/* Will add additional fields from HourlyForecast later */}
                </div>
            ))}
        </div>
    );
}

export default HourlyForecastCard;