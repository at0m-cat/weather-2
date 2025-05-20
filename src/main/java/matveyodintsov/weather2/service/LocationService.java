package matveyodintsov.weather2.service;

import com.fasterxml.jackson.databind.JsonNode;
import matveyodintsov.weather2.config.OpenWeatherConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class LocationService {

    private final WebClient webClient;
    private final OpenWeatherConfig config;

    public LocationService(@Qualifier("location") WebClient webClient, OpenWeatherConfig config) {
        this.webClient = webClient;
        this.config = config;
    }

    public Flux<JsonNode> getLocations(String city) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q", city)
                        .queryParam("limit", 5)
                        .queryParam("appid", config.getKey())
                        .build())
                .retrieve()
                .bodyToFlux(JsonNode.class);
    }


}
