package matveyodintsov.weather2.controller;

import lombok.RequiredArgsConstructor;
import matveyodintsov.weather2.dto.LocationDto;
import matveyodintsov.weather2.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    @GetMapping()
    public String getLocations(@RequestParam(value = "loc", required = false) String loc, Model model) {
        if (loc == null || loc.isEmpty()) {
            model.addAttribute("location", null);
            return "search-results";
        }
        model.addAttribute("locations", locationService.findLocationsByName(loc));
        return "search-results";
    }

    @PostMapping("/save")
    public String saveLocation(@ModelAttribute LocationDto location) {
        locationService.save(location);
        return "redirect:/";
    }

}
