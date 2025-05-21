package matveyodintsov.weather2.controller;

import lombok.RequiredArgsConstructor;
import matveyodintsov.weather2.dto.ConsumerDto;
import matveyodintsov.weather2.exception.LoginException;
import matveyodintsov.weather2.exception.RegistrationException;
import matveyodintsov.weather2.service.ConsumerDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ConsumerDetailService consumerDetailService;

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if ("true".equalsIgnoreCase(error)) {
            throw new LoginException("Login failed");
        }
        model.addAttribute("consumer", new ConsumerDto());
        return "sign-in";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("consumer", new ConsumerDto());
        return "sign-up";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute("consumer") ConsumerDto consumer) {
        try {
            if (!consumer.getPassword().equals(consumer.getRepeatPassword())) {
                throw new RegistrationException("Passwords don't match");
            }
            consumerDetailService.registerNewConsumer(consumer);
            return "redirect:/auth/login";
        } catch (RegistrationException e) {
            return "error/sign-up-with-errors";
        }
    }

}
