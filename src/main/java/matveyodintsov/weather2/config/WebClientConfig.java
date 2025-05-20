package matveyodintsov.weather2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "weather")
    public WebClient openWeatherMapWebClientWeatherData(OpenWeatherConfig config) {
        return WebClient.builder()
                .baseUrl(config.getWeather())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean(name = "location")
    public WebClient openWeatherMapWebClientLocationData(OpenWeatherConfig config) {
        return WebClient.builder()
                .baseUrl(config.getLocation())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }


}
