package matveyodintsov.weather2.controller;

import matveyodintsov.weather2.exception.RegistrationException;
import matveyodintsov.weather2.exception.LoginException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviceExceptionHandler{

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error/error";
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public String handleUsernameNotFoundException(UsernameNotFoundException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error/error";
    }

    @ExceptionHandler(RegistrationException.class)
    public String handleAuthorizationException() {
        return "error/sign-up-with-errors";
    }

    @ExceptionHandler(LoginException.class)
    public String handleLoginException() {
        return "error/sign-in-with-errors";
    }

}
