package domain.weather.repository;

import domain.city.City;
import domain.weather.Weather;
import domain.weather.factory.FactoryException;
import domain.weather.factory.OpenWeatherFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import service.OpenWeatherClient;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherRepositoryTest {
    private OpenWeatherRepository subject;
    private Weather result = new Weather(null, null, null, null);

    @Mock
    private OpenWeatherClient clientMock;

    @Mock
    private OpenWeatherFactory factoryMock;

    @Before
    public void setUp() throws FactoryException {
        subject = new OpenWeatherRepository(clientMock, factoryMock);
        when(clientMock.get("/weather?id=12345")).thenReturn("{\"example\":\"API Response String\"}");
        when(factoryMock.getByString("{\"example\":\"API Response String\"}")).thenReturn(result);
    }

    @After
    public void tearDown() {
        subject = null;
    }

    @Test
    public void itReturnsWeatherByCity() throws RepositoryException {
        assertThat(subject.getByCity(new City("12345", "Test city", "PL"))).isEqualTo(result);
    }

    @Test
    public void itThrowsRepositoryExceptionIfFactoryIsUnableToParseResponse() throws FactoryException {
        when(factoryMock.getByString("{\"example\":\"API Response String\"}")).thenThrow(new FactoryException("Could not parse JSON"));
        assertThatExceptionOfType(RepositoryException.class).isThrownBy(() -> subject.getByCity(new City("12345", "Test city", "PL")));
    }
}
