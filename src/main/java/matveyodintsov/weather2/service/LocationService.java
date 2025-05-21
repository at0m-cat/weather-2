package matveyodintsov.weather2.service;

import com.fasterxml.jackson.databind.JsonNode;
import matveyodintsov.weather2.adapter.LocationAdapter;
import matveyodintsov.weather2.config.OpenWeatherClientConfig;
import matveyodintsov.weather2.dto.LocationDto;
import matveyodintsov.weather2.event.LocationSavedEvent;
import matveyodintsov.weather2.mapper.LocationMapper;
import matveyodintsov.weather2.repo.LocationRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class LocationService {

    private final WebClient webClient;
    private final OpenWeatherClientConfig config;
    private final LocationAdapter locationAdapter;
    private final LocationRepo repo;
    private final LocationMapper mapper;
    private final ApplicationEventPublisher publisher;

    public LocationService(@Qualifier("location") WebClient webClient,
                           OpenWeatherClientConfig config,
                           LocationAdapter locationAdapter,
                           LocationRepo repo,
                           LocationMapper mapper,
                           ApplicationEventPublisher publisher) {
        this.webClient = webClient;
        this.config = config;
        this.locationAdapter = locationAdapter;
        this.repo = repo;
        this.mapper = mapper;
        this.publisher = publisher;
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
                .map(locationAdapter::fromJson)
                .collectList()
                .block();

        return Objects.requireNonNull(locations).isEmpty() ?
                Collections.emptyList() : Collections.unmodifiableList(locations);
    }

    public void save(LocationDto locationDto) {
        var location = mapper.fromDto(locationDto);
        if (!repo.existsLocationByLonAndLat(location.getLon(), location.getLat())) {
            location = repo.save(location);
            locationDto.setId(location.getId());
        }

        publisher.publishEvent(new LocationSavedEvent(this, locationDto));
    }

}
