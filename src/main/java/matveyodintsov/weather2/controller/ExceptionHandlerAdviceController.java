package matveyodintsov.weather2.controller;

import matveyodintsov.weather2.exception.LoginException;
import matveyodintsov.weather2.exception.RegistrationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerAdviceController {

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error/error";
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public String handleUsernameNotFoundException(UsernameNotFoundException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error/error";
    }

    @ExceptionHandler(RegistrationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handleAuthorizationException() {
        return "error/sign-up-with-errors";
    }

    @ExceptionHandler(LoginException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handleLoginException() {
        return "error/sign-in-with-errors";
    }

}
