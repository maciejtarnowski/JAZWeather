package domain.city;

public class City {
    private String cityId;
    private String name;
    private String countryCode;

    public City(String cityId, String name, String countryCode) {
        this.cityId = cityId;
        this.name = name;
        this.countryCode = countryCode;
    }

    public String getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
