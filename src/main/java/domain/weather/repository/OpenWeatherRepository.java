package domain.weather.repository;

import domain.city.City;
import domain.weather.Weather;
import domain.weather.factory.FactoryException;
import domain.weather.factory.OpenWeatherFactory;
import service.OpenWeatherClient;

public class OpenWeatherRepository implements WeatherRepository {
    private OpenWeatherClient client;
    private OpenWeatherFactory factory;

    public OpenWeatherRepository(OpenWeatherClient client, OpenWeatherFactory factory) {
        this.client = client;
        this.factory = factory;
    }

    public Weather getByCity(City city) throws RepositoryException {
        try {
            return factory.getByString(
                    city,
                    client.get("/weather?id=" + city.getCityId())
            );
        } catch (FactoryException e) {
            throw new RepositoryException("Could not get weather data");
        }
    }
}
