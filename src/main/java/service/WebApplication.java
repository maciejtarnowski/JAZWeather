package service;

import com.github.slugify.Slugify;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import domain.city.City;
import domain.city.CityRepository;
import domain.city.RepositoryException;
import domain.city.RepositoryNotInitializedException;
import domain.weather.Weather;
import domain.weather.factory.OpenWeatherFactory;
import domain.weather.repository.OpenWeatherRepository;
import domain.weather.repository.WeatherRepository;
import org.json.simple.parser.JSONParser;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class WebApplication {
    public List<City> listCities() throws WebApplicationException {
        try {
            return getCityRepository().getAll();
        } catch (RepositoryNotInitializedException e) {
            throw new WebApplicationException("Internal error 02");
        }
    }

    public Weather weatherByRequest(HttpServletRequest request) throws WebApplicationException {
        validateRequest(request);

        WeatherRepository weatherRepository = new OpenWeatherRepository(
            new OpenWeatherClient(
                    Client.create(new DefaultClientConfig())
            ),
            new OpenWeatherFactory(new JSONParser())
        );

        City city;
        try {
            city = getCityRepository().getBySlug(request.getParameter("s"));
            return weatherRepository.getByCity(city);
        } catch (RepositoryNotInitializedException e) {
            throw new WebApplicationException("Internal error 03");
        } catch (RepositoryException e) {
            throw new WebApplicationException("City not available");
        } catch (domain.weather.repository.RepositoryException e) {
            throw new WebApplicationException("Internal error 04");
        }
    }

    private CityRepository getCityRepository() throws WebApplicationException {
        CityRepository repository;
        try {
            repository = new CityRepository(new Slugify());
        } catch (IOException e) {
            throw new WebApplicationException("Internal error 01");
        }
        repository.init();

        return repository;
    }

    private void validateRequest(HttpServletRequest request) throws WebApplicationException {
        if  (request.getParameter("s") == null) {
            throw new WebApplicationException("Internal error 05");
        }
    }
}
