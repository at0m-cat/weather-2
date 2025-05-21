package matveyodintsov.weather2.service;

import com.fasterxml.jackson.databind.JsonNode;
import matveyodintsov.weather2.adapter.LocationAdapter;
import matveyodintsov.weather2.config.OpenWeatherClientConfig;
import matveyodintsov.weather2.dto.LocationDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.List;

@Service
public class LocationService {

    private final WebClient webClient;
    private final OpenWeatherClientConfig config;
    private final LocationAdapter locationAdapter;

    public LocationService(@Qualifier("location") WebClient webClient, OpenWeatherClientConfig config, LocationAdapter locationAdapter) {
        this.webClient = webClient;
        this.config = config;
        this.locationAdapter = locationAdapter;
    }

    public List<LocationDto> getLocations(String city) {
        var locations = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q", city)
                        .queryParam("limit", 5)
                        .queryParam("appid", config.getKey())
                        .build())
                .retrieve()
                .bodyToFlux(JsonNode.class)
                .map(locationAdapter::fromJson)
                .collectList()
                .block();

        return Collections.unmodifiableList(locations);
    }

}
