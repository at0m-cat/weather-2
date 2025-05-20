package matveyodintsov.weather2.controller;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import matveyodintsov.weather2.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/weather")
@AllArgsConstructor
public class WeatherController {

    private WeatherService weatherService;

    @GetMapping("/{city}")
    public Mono<JsonNode> getWeather(@PathVariable String city) {
        return weatherService.getWeather(city);
    }
}
