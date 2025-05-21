package matveyodintsov.weather2.event;

import lombok.RequiredArgsConstructor;
import matveyodintsov.weather2.dto.LocationDto;
import matveyodintsov.weather2.service.WeatherService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherEventListener {

    private final WeatherService weatherService;

    @Async
    @EventListener
    public void onWeatherCreated(WeatherCreatedEvent event) {
        LocationDto location = event.getLocation();
        weatherService.fetchAndSaveWeatherData(location);
    }
}