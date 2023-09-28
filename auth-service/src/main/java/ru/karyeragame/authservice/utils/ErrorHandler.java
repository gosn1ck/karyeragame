package ru.karyeragame.authservice.utils;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestControllerAdvice(basePackages = "ru.karyeragame.authservice")
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, String> handleUsernameNotFoundException(final UsernameNotFoundException e) {
        return makeMessage(e, HttpStatus.UNAUTHORIZED, "Authorization failed");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleIllegalArgumentException(final IllegalArgumentException e) {
        return makeMessage(e, HttpStatus.BAD_REQUEST, "Incorrectly made request.");
    }

    private Map<String, String> makeMessage(Exception e, HttpStatus status, String reason) {
        Optional<String> messageOptional = Optional.ofNullable(e.getMessage());
        String message = messageOptional.orElse("-");
        return Map.of("status", status.toString(),
                "reason", reason,
                "message", message,
                "timestamp", LocalDateTime.now().toString()
        );
    }
}
