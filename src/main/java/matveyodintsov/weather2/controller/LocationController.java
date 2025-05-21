package matveyodintsov.weather2.controller;

import lombok.AllArgsConstructor;
import matveyodintsov.weather2.repo.LocationRepo;
import matveyodintsov.weather2.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;
    private final LocationRepo repo;

    @GetMapping()
    public String getLocations(@RequestParam("loc") String loc, Model model) {
        var locations = locationService.findLocationsByName(loc);
        model.addAttribute("locations", locations);
        return "search-results";
    }

}
