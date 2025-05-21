package matveyodintsov.weather2.controller;


import lombok.AllArgsConstructor;
import matveyodintsov.weather2.service.WeatherService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@AllArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping()
    public String getWeather(Model model) {

        return "index";
    }

}
