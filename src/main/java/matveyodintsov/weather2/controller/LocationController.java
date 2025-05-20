package matveyodintsov.weather2.controller;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import matveyodintsov.weather2.repo.LocationRepo;
import matveyodintsov.weather2.service.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;
    private final LocationRepo repo;

    @GetMapping("/{loc}")
    public Flux<JsonNode> getLocations(@PathVariable String loc) {
        return locationService.getLocations(loc);
    }

}
