package domain.weather.parameter;

public class Pressure {
    private Double value;

    public Pressure(Double value) {
        this.value = value;
    }

    public Integer getValue() {
        return value.intValue();
    }

    @Override
    public String toString() {
        return getValue() + " hPa";
    }
}
