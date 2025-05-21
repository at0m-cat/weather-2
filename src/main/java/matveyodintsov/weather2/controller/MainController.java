package matveyodintsov.weather2.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class MainController {


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("login", SecurityContextHolder.getContext().getAuthentication().getName());
        return "index";
    }

}
