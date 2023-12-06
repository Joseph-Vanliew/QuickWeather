import React from 'react';

function CurrentWeatherCard({ current, city }) {
    const first = current?.weather[0];

    const formatHour = (hour) => {
        const hour12 = hour % 12 || 12; // Convert 0-23 hour to 12-hour format
        const amPm = hour < 12 ? 'AM' : 'PM';
        return `${hour12}${":00"}${amPm}`;
    };

    return (
        <div className={"card weather-header"}>
            <h2>Weather For {city}</h2>
            <p className={"temperature"}>{Math.round(current?.temp)}°F</p>
            <div className={"weather-details"}>
                <p>Feels Like: {Math.round(current?.feels_like)}°F</p>
                <p>Humidity: {current?.humidity}%</p>
                <p>Wind: {Math.round(current?.wind_speed)} mph</p>
            </div>
            <p>{new Date(current.dt * 1000).toLocaleDateString('en-US', { weekday: 'long' })}</p>
            <p>{formatHour(new Date(current.dt * 1000).getHours())} *local to you*</p>

            {first && (
                <div className={"description"}>
                    <p>{first.description}</p>
                    {/* <p>Icon: {first.icon}</p> */}
                </div>
            )}
        </div>
    );
}
export default CurrentWeatherCard;