package matveyodintsov.weather2.event;

import lombok.Getter;
import matveyodintsov.weather2.dto.LocationDto;
import org.springframework.context.ApplicationEvent;

@Getter
public class WeatherCreatedEvent extends ApplicationEvent {

    private final LocationDto location;

    public WeatherCreatedEvent(Object source, LocationDto location) {
        super(source);
        this.location = location;
    }
}