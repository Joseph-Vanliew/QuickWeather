import React, { useState } from 'react';
import LandingPage from './components/LandingPage';
import WeatherDisplay from './components/WeatherDisplay';

function App() {
    const [city, setCity] = useState('');

    return (
        <div>
            {!city ? (
                <LandingPage onCitySubmit={setCity} />
            ) : (
                <WeatherDisplay initialCity={city} />
            )}
        </div>
    );
}

export default App;