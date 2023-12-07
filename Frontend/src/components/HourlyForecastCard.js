import React from 'react';

function HourlyForecastCard({ hourly }) {
    const limitedHourlyData = hourly?.slice(0, 5);

    const formatHour = (hour) => {
        const hour12 = hour % 12 || 12; // Convert 0-23 hour to 12-hour format
        const amPm = hour < 12 ? 'AM' : 'PM';
        return `${hour12}${":00"}${amPm}`;
    };

    return (
        <div className={" card weather-header"}>
            <h2>Hourly</h2>
            {limitedHourlyData?.map((forecast, index) => (
                <div key={index} className={"hour"}>
                    <p>{formatHour(new Date(forecast.dt * 1000).getHours())}</p>
                    <p>Temp: {Math.round(forecast.temp)}°</p>
                    <p>Rain%: {Math.round(forecast.pop * 100)}%</p>
                </div>
            ))}
        </div>
    );
}

export default HourlyForecastCard;