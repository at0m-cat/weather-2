package matveyodintsov.weather2.controller;

import lombok.RequiredArgsConstructor;
import matveyodintsov.weather2.service.ConsumerDetailService;
import matveyodintsov.weather2.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class WeatherController {

    private final ConsumerDetailService consumerDetailService;
    private final WeatherService weatherService;

    @GetMapping()
    public String index(Model model) {
        String authenticateUsername = consumerDetailService.getConsumerAuthenticatedUsername();
        var weather = weatherService.getWeatherByAuthenticateUsername(authenticateUsername);
        model.addAttribute("login", authenticateUsername);
        model.addAttribute("weatherData", weather);
        return "index";
    }

}
