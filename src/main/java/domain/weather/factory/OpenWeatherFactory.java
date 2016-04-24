package domain.weather.factory;

import domain.weather.Weather;
import domain.weather.parameter.Clouds;
import domain.weather.parameter.Pressure;
import domain.weather.parameter.Temperature;
import domain.weather.parameter.WindSpeed;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static java.lang.Math.toIntExact;

public class OpenWeatherFactory implements WeatherFactory {
    private JSONParser jsonParser;

    public OpenWeatherFactory(JSONParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    @Override
    public Weather getByString(String response) throws FactoryException {
        JSONObject parsedResponse = parseJson(response);

        return new Weather(getTemperature(parsedResponse), getPressure(parsedResponse), getWindSpeed(parsedResponse), getClouds(parsedResponse));
    }

    private JSONObject parseJson(String response) throws FactoryException {
        try {
            return (JSONObject) jsonParser.parse(response);
        } catch (ParseException e) {
            throw new FactoryException("Could not parse JSON");
        }
    }

    private Clouds getClouds(JSONObject parsedData) {
        return new Clouds(toIntExact((long) getCloudsObject(parsedData).get("all")));
    }

    private WindSpeed getWindSpeed(JSONObject parsedData) {
        return new WindSpeed((Double) getWindObject(parsedData).get("speed"));
    }

    private Pressure getPressure(JSONObject parsedData) {
        return new Pressure(toIntExact((long) getMainObject(parsedData).get("pressure")));
    }

    private Temperature getTemperature(JSONObject parsedData) {
        return new Temperature((Double) getMainObject(parsedData).get("temp"));
    }

    private JSONObject getMainObject(JSONObject parsedData) {
        return (JSONObject) parsedData.get("main");
    }

    private JSONObject getWindObject(JSONObject parsedData) {
        return (JSONObject) parsedData.get("wind");
    }

    private JSONObject getCloudsObject(JSONObject parsedData) {
        return (JSONObject) parsedData.get("clouds");
    }
}
