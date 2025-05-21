package matveyodintsov.weather2.controller;

import lombok.AllArgsConstructor;
import matveyodintsov.weather2.dto.LocationDto;
import matveyodintsov.weather2.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    @GetMapping()
    public String getLocations(@RequestParam("loc") String loc, Model model) {
        var locations = locationService.findLocationsByName(loc);
        model.addAttribute("locations", locations);
        return "search-results";
    }

    @PostMapping("/save")
    public String saveLocation(@ModelAttribute LocationDto location) {
        locationService.save(location);
        return "redirect:/";
    }

}
