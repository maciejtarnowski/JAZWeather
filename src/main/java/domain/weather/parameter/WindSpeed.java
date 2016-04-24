package domain.weather.parameter;

public class WindSpeed {
    private Double value;

    public WindSpeed(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString() + " m/s";
    }
}
