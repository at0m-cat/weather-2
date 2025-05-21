package matveyodintsov.weather2.service;

import com.fasterxml.jackson.databind.JsonNode;
import matveyodintsov.weather2.adapter.WeatherAdapter;
import matveyodintsov.weather2.config.OpenWeatherClientConfig;
import matveyodintsov.weather2.dto.LocationDto;
import matveyodintsov.weather2.dto.WeatherDto;
import matveyodintsov.weather2.mapper.LocationMapper;
import matveyodintsov.weather2.mapper.WeatherMapper;
import matveyodintsov.weather2.model.Location;
import matveyodintsov.weather2.model.Weather;
import matveyodintsov.weather2.repo.LocationRepo;
import matveyodintsov.weather2.repo.WeatherRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class WeatherService {

    private final WebClient webClient;
    private final OpenWeatherClientConfig openWeatherConfig;
    private final WeatherMapper weatherMapper;
    private final LocationMapper locationMapper;
    private final WeatherAdapter weatherAdapter;
    private final WeatherRepo weatherRepo;
    private final LocationRepo locationRepo;

    public WeatherService(@Qualifier("weather") WebClient webClient,
                          WeatherRepo weatherRepo,
                          LocationRepo locationRepo,
                          OpenWeatherClientConfig openWeatherConfig,
                          WeatherMapper weatherMapper,
                          LocationMapper locationMapper,
                          WeatherAdapter weatherAdapter,
                          ConsumerDetailService consumerDetailService,
                          ApplicationEventPublisher publisher) {
        this.webClient = webClient;
        this.weatherRepo = weatherRepo;
        this.locationRepo = locationRepo;
        this.openWeatherConfig = openWeatherConfig;
        this.weatherMapper = weatherMapper;
        this.locationMapper = locationMapper;
        this.weatherAdapter = weatherAdapter;
    }

    public void fetchAndSaveWeatherData(LocationDto locationDto) {
        Location location = locationRepo.findByLatAndLon(locationDto.getLat(), locationDto.getLon())
                .orElseGet(() -> locationMapper.fromDto(locationDto));

        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("lat", locationDto.getLat())
                        .queryParam("lon", locationDto.getLon())
                        .queryParam("units", "metric")
                        .queryParam("appid", openWeatherConfig.getKey())
                        .build())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(weatherAdapter::fromJson)
                .doOnNext(weatherDto -> {
                    weatherDto.setLocation(locationDto);
                    Weather weather = weatherMapper.toWeather(weatherDto);
                    weather.setLocation(location);
                    weatherRepo.save(weather);
                })
                .block();
    }


}
