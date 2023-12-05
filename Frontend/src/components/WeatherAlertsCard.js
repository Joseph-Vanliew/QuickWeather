import React from 'react';

function WeatherAlertsCard({ alerts }) {
    return (
        <div className={"weather-card weather-alerts"}>
            {alerts?.length > 0 ? (
                <div>
                    <h2>Weather Alerts</h2>
                    {alerts.map((alert, index) => (
                        <div key={index}>
                            <p>Event: {alert.event}</p>
                            <p>Description: {alert.description}</p>
                            {/* Will add additional fields later WeatherAlert */}
                        </div>
                    ))}
                </div>
            ) : (
                <p>No current weather alerts.</p>
            )}
        </div>
    );
}

export default WeatherAlertsCard;