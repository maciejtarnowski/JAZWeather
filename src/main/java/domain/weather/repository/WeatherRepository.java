package domain.weather.repository;

import domain.city.City;
import domain.weather.Weather;

public interface WeatherRepository {
    Weather getByCity(City city) throws RepositoryException;
}
