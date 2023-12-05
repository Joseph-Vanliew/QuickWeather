import React from 'react';

function DailyForecastCard({ daily }) {
    return (
        <div className={"weather-card daily-forecast"}>
            <h2>Daily</h2>
            {daily?.map((forecast, index) => (
                <div key={index}>
                    <p>Date: {new Date(forecast.dt * 1000).toLocaleDateString()}</p>
                    <p>Day Temperature: {Math.round(forecast.temp.day)}°F</p>
                    {/* Will add additional fields later to DailyForecast */}
                </div>
            ))}
        </div>
    );
}

export default DailyForecastCard;