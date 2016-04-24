package domain.city;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.assertj.core.api.Assertions.*;

@RunWith(JUnit4.class)
public class CityRepositoryTest {
    private CityRepository subject;

    @Before
    public void setUp() {
        subject = new CityRepository();
        subject.init();
    }

    @After
    public void tearDown() {
        subject = null;
    }

    @Test
    public void itThrowsExceptionIfItIsNotInitialized() throws RepositoryNotInitializedException {
        subject = new CityRepository();
        assertThatExceptionOfType(RepositoryNotInitializedException.class).isThrownBy(() -> subject.getAll());
    }

    @Test
    public void itReturnsAllCities() throws RepositoryNotInitializedException {
        assertThat(subject.getAll()).isNotNull();
        assertThat(subject.getAll()).hasSize(7);
    }
}
