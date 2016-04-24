package service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class OpenWeatherClient implements WeatherClient {
    private final static String apiKey = "470db4605f89e75b2a905bd8bd09f9ff";
    private final static String endpoint = "http://api.openweathermap.org/data/2.5";

    private Client httpClient;

    public OpenWeatherClient(Client httpClient) {
        this.httpClient = httpClient;
    }

    public String get(String url) {
        WebResource resource = httpClient.resource(endpoint + getUrlWithMandatoryParams(url));

        return resource.type("application/json").get(String.class);
    }

    private String getUrlWithMandatoryParams(String url) {
        String glue;
        if (url.contains("?")) {
            glue = "&";
        } else {
            glue = "?";
        }
        return joinUrlWithMandatoryParamsUsingString(url, glue);
    }

    private String joinUrlWithMandatoryParamsUsingString(String url, String glue) {
        return url + glue + getAppIdParameter() + "&units=metric";
    }

    private String getAppIdParameter() {
        return "APPID=" + apiKey;
    }
}
