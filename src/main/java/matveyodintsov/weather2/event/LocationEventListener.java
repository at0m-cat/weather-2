package matveyodintsov.weather2.event;

import lombok.RequiredArgsConstructor;
import matveyodintsov.weather2.dto.LocationDto;
import matveyodintsov.weather2.mapper.LocationMapper;
import matveyodintsov.weather2.model.Consumer;
import matveyodintsov.weather2.repo.ConsumerRepo;
import matveyodintsov.weather2.repo.LocationRepo;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class LocationEventListener {

    private final ConsumerRepo consumerRepo;
    private final LocationRepo locationRepo;
    private final LocationMapper mapper;
    private final ApplicationEventPublisher publisher;

    @Transactional
    @EventListener
    public void onLocationSaved(LocationSavedEvent event) {
        LocationDto dto = event.getLocation();

        var userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Consumer user = consumerRepo.findByName(userName)
                .orElseThrow(() -> new RuntimeException("User does not exist"));

        var location = locationRepo.findByLatAndLon(dto.getLat(), dto.getLon())
                .orElseGet(() -> locationRepo.save(mapper.fromDto(dto)));

        user.getLocations().add(location);
        consumerRepo.save(user);

        publisher.publishEvent(new WeatherCreatedEvent(this, dto));
    }
}