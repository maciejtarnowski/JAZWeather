package domain.weather.factory;

import domain.weather.Weather;

public interface WeatherFactory {
    Weather getByString(String response) throws FactoryException;
}
