package domain.weather.parameter;

public class Pressure {
    private Integer value;

    public Pressure(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + " hPa";
    }
}
