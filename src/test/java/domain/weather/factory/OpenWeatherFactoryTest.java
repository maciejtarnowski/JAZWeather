package domain.weather.factory;

import domain.city.City;
import domain.weather.Weather;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.assertj.core.api.Assertions.*;

@RunWith(JUnit4.class)
public class OpenWeatherFactoryTest {
    private OpenWeatherFactory subject;

    @Before
    public void setUp() {
        subject = new OpenWeatherFactory(new JSONParser());
    }

    @After
    public void tearDown() {
        subject = null;
    }

    @Test
    public void itBuildsWeatherByJsonString() throws FactoryException {
        String sampleJson = "{\"coord\":{\"lon\":145.77,\"lat\":-16.92},\"weather\":[{\"id\":521,\"main\":\"Rain\",\"description\":\"shower rain\",\"icon\":\"09n\"}],\"base\":\"cmc stations\",\"main\":{\"temp\":23.22,\"pressure\":1016,\"humidity\":88,\"temp_min\":20,\"temp_max\":25.56},\"wind\":{\"speed\":4.6,\"deg\":150},\"clouds\":{\"all\":75},\"dt\":1461500406,\"sys\":{\"type\":1,\"id\":8166,\"message\":0.0134,\"country\":\"AU\",\"sunrise\":1461443256,\"sunset\":1461484927},\"id\":2172797,\"name\":\"Cairns\",\"cod\":200}";

        Weather results = subject.getByString(new City("1", "Test City", "PL"), sampleJson);

        assertThat(results.getClouds()).hasToString("75%");
        assertThat(results.getPressure()).hasToString("1016 hPa");
        assertThat(results.getWindSpeed()).hasToString("4.6 m/s");
        assertThat(results.getTemperature()).hasToString("23.22 &deg;C");
    }
}
