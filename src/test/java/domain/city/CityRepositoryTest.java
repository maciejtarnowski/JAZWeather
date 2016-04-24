package domain.city;

import com.github.slugify.Slugify;
import domain.weather.repository.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

@RunWith(JUnit4.class)
public class CityRepositoryTest {
    private CityRepository subject;

    @Before
    public void setUp() throws IOException {
        subject = new CityRepository(new Slugify());
        subject.init();
    }

    @After
    public void tearDown() {
        subject = null;
    }

    @Test
    public void itThrowsExceptionIfItIsNotInitialized() throws RepositoryNotInitializedException, IOException {
        subject = new CityRepository(new Slugify());
        assertThatExceptionOfType(RepositoryNotInitializedException.class).isThrownBy(() -> subject.getAll());
    }

    @Test
    public void itReturnsAllCities() throws RepositoryNotInitializedException {
        assertThat(subject.getAll()).isNotNull();
        assertThat(subject.getAll()).hasSize(7);
    }

    @Test
    public void itReturnsCityBySlug() throws RepositoryNotInitializedException, RepositoryException {
        assertThat(subject.getBySlug("gdansk")).isEqualToComparingFieldByField(new City("3099434", "Gdańsk", "PL"));
        assertThat(subject.getBySlug("lodz")).isEqualToComparingFieldByField(new City("3093133", "Łódź", "PL"));
    }
}
