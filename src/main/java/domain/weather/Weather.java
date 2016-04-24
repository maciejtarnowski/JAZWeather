package domain.weather;

import domain.weather.parameter.Clouds;
import domain.weather.parameter.Pressure;
import domain.weather.parameter.Temperature;
import domain.weather.parameter.WindSpeed;

public class Weather {
    private Temperature temperature;
    private Pressure pressure;
    private WindSpeed windSpeed;
    private Clouds clouds;

    public Weather(Temperature temperature, Pressure pressure, WindSpeed windSpeed, Clouds clouds) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.clouds = clouds;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Pressure getPressure() {
        return pressure;
    }

    public WindSpeed getWindSpeed() {
        return windSpeed;
    }

    public Clouds getClouds() {
        return clouds;
    }
}
