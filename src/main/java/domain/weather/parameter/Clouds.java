package domain.weather.parameter;

public class Clouds {
    private Integer percentage;

    public Clouds(Integer percentage) {
        this.percentage = percentage;
    }

    public Integer getPercentage() {
        return percentage;
    }

    @Override
    public String toString() {
        return percentage + "%";
    }
}
