package matveyodintsov.weather2.service;

import com.fasterxml.jackson.databind.JsonNode;
import matveyodintsov.weather2.mapper.LocationMapper;
import matveyodintsov.weather2.config.OpenWeatherClientConfig;
import matveyodintsov.weather2.dto.LocationDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class LocationService {

    private final WebClient webClient;
    private final OpenWeatherClientConfig config;
    private final LocationMapper locationMapper;

    public LocationService(@Qualifier("location") WebClient webClient,
                           OpenWeatherClientConfig config,
                           LocationMapper locationAdapter) {
        this.webClient = webClient;
        this.config = config;
        this.locationMapper = locationAdapter;
    }

    public List<LocationDto> findLocationsByName(String city) {
        var locations = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q", city)
                        .queryParam("limit", 5)
                        .queryParam("appid", config.getKey())
                        .build())
                .retrieve()
                .bodyToFlux(JsonNode.class)
                .map(locationMapper::fromJson)
                .collectList()
                .block();

        return Objects.requireNonNull(locations).isEmpty() ?
                Collections.emptyList() : Collections.unmodifiableList(locations);
    }

}
