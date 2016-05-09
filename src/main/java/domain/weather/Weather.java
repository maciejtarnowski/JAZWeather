package domain.weather;

import domain.city.City;
import domain.weather.parameter.Clouds;
import domain.weather.parameter.Pressure;
import domain.weather.parameter.Temperature;
import domain.weather.parameter.WindSpeed;

public class Weather {
    private City city;
    private Temperature temperature;
    private Pressure pressure;
    private WindSpeed windSpeed;
    private Clouds clouds;

    public Weather(City city, Temperature temperature, Pressure pressure, WindSpeed windSpeed, Clouds clouds) {
        this.city = city;
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

    public City getCity() {
        return city;
    }
}
