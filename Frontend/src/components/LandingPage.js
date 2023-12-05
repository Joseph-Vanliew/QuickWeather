import React, { useState } from 'react';

function LandingPage({ onCitySubmit }) {
    const [city, setCity] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        onCitySubmit(city);
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <label>
                    Enter City:
                    <input type="text" value={city} onChange={(e) => setCity(e.target.value)} />
                </label>
                <button type="submit">Get Weather</button>
            </form>
        </div>
    );
}

export default LandingPage;