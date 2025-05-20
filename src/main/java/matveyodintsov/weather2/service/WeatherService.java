package matveyodintsov.weather2.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import matveyodintsov.weather2.config.OpenWeatherConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class WeatherService {

    private WebClient wc;
    private OpenWeatherConfig wcConfig;

    public Mono<JsonNode> getWeather(String city) {

        return wc.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/weather")
                        .queryParam("q", city)
                        .queryParam("appid", wcConfig.getKey())
                        .build())
                .retrieve()
                .bodyToMono(JsonNode.class);
    }


}
