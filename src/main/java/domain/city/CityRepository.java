package domain.city;

import com.github.slugify.Slugify;

import java.util.ArrayList;
import java.util.List;

public class CityRepository {
    private Slugify slugify;

    private List<City> cities;

    public CityRepository(Slugify slugify) {
        this.slugify = slugify;
    }

    public void init() {
        cities = new ArrayList<>();

        cities.add(new City("4927854", "Warszawa", "PL"));
        cities.add(new City("3099434", "Gdańsk", "PL"));
        cities.add(new City("3094802", "Kraków", "PL"));
        cities.add(new City("3081368", "Wrocław", "PL"));
        cities.add(new City("7530858", "Poznań", "PL"));
        cities.add(new City("3093133", "Łódź", "PL"));
        cities.add(new City("3096472", "Katowice", "PL"));
    }

    public List<City> getAll() throws RepositoryNotInitializedException {
        if (cities == null) {
            throw new RepositoryNotInitializedException("Repository is not initialized");
        }
        return cities;
    }

    public City getBySlug(String slug) throws RepositoryNotInitializedException, RepositoryException {
        for (City city : getAll()) {
            if (slug.equals(slugify.slugify(city.getName()))) {
                return city;
            }
        }
        throw new RepositoryException("City not found");
    }
}
