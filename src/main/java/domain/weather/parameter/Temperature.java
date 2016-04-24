package domain.weather.parameter;

public class Temperature {
    private Double value;

    public Temperature(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString() + " &deg;C";
    }
}
