import React from 'react';

function DailyForecastCard({ daily }) {

    const limitedDailyData = daily?.slice(0, 5);

    return (
        <div className={"card weather-header"}>
            <h2>5 Day </h2>
            {limitedDailyData?.map((forecast, index) => (
                <div key={index} className={"day"}>
                    <p>Date: {new Date(forecast.dt * 1000).toLocaleDateString('en-US', { month: 'short', day: 'numeric' })}</p>
                    <p>Day Temp: {Math.round(forecast.temp.day)}°F</p>
                    <p>Low/High: {Math.round(forecast.temp.min)}°- {Math.round(forecast.temp.max)}°</p>
                </div>
            ))}
        </div>
    );
}

export default DailyForecastCard;