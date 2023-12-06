import React from 'react';

function WeatherAlertsCard({ alerts }) {
    const alert = alerts && alerts.length > 0 ? alerts[0] : null;

    return (
        <div className={"card weather-alerts"}>
            {alert ? (
                <div>
                    <h2 className={"h2"}>Weather Alerts</h2>
                    <div>
                        <p>From: {alert.sender_name}</p>
                        <p>Event: {alert.event}</p>
                        <p>Description: {alert.description}</p>
                        <p>Start: {new Date(alert.start * 1000).toLocaleString()}</p>
                        <p>End: {new Date(alert.end * 1000).toLocaleString()}</p>
                    </div>
                </div>
            ) : (
                <p>No current weather alerts.</p>
            )}
        </div>
    );
}

export default WeatherAlertsCard;
