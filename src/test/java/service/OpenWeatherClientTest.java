package service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherClientTest {
    private OpenWeatherClient subject;

    @Mock
    private Client clientMock;

    @Mock
    private WebResource resourceMock;

    @Mock
    private WebResource.Builder builderMock;

    @Before
    public void setUp() {
        subject = new OpenWeatherClient(clientMock);
        when(builderMock.get(String.class)).thenReturn("{\"example\":\"API Response String\"}");
        when(resourceMock.type("application/json")).thenReturn(builderMock);
        when(clientMock.resource("http://api.openweathermap.org/data/2.5/example?APPID=470db4605f89e75b2a905bd8bd09f9ff&units=metric")).thenReturn(resourceMock);
    }

    @After
    public void tearDown() {
        subject = null;
    }

    @Test
    public void itPerformsGetRequestAndReturnsDataAsString() {
        assertThat(subject.get("/example")).isEqualTo("{\"example\":\"API Response String\"}");
    }

    @Test
    public void itUsesAmpersandAsGlueInUrlIfSomeParamsAreAlreadyPresent() {
        when(clientMock.resource(
                "http://api.openweathermap.org/data/2.5/example?some=param&APPID=470db4605f89e75b2a905bd8bd09f9ff&units=metric"
        )).thenReturn(resourceMock);

        assertThat(subject.get("/example?some=param")).isEqualTo("{\"example\":\"API Response String\"}");
    }
}
