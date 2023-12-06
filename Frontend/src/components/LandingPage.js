import React, { useState } from 'react';
import '../css.styles/LandingPage.css';

function LandingPage({ onCitySubmit }) {
    const [city, setCity] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        onCitySubmit(city);
    };

    return (
        <div className={"landing-page-container"}>
            <div className={"cloud"}></div>
            <div className={"cloud"}></div>
            <div className={"cloud"}></div>
            <h1>Quick Weather</h1>
            <form onSubmit={handleSubmit} className={"landing-form"}>
                <label>
                    <input
                        type="text"
                        value={city}
                        onChange={(e) => setCity(e.target.value)}
                        placeholder="Ex: Grand Rapids"
                    />
                </label>
                <button type="submit">Get Weather</button>
            </form>
        </div>
    );
}

export default LandingPage;