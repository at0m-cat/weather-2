package matveyodintsov.weather2.service;

import matveyodintsov.weather2.config.OpenWeatherConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherService {

    private final WebClient webClient;
    private final OpenWeatherConfig openWeatherConfig;

    public WeatherService(@Qualifier("weather") WebClient webClient, OpenWeatherConfig openWeatherConfig) {
        this.webClient = webClient;
        this.openWeatherConfig = openWeatherConfig;
    }


}
