package ru.karyeragame.paymentsystem.exceptions;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Hidden
@Slf4j
public class ErrorHandler {

    @ExceptionHandler({NotFoundException.class, EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(final Exception e) {
        log.error("Стек трейс ошибки: {}", e.getStackTrace());
        return new ErrorResponse(HttpStatus.NOT_FOUND,
                e.getMessage(),
                LocalDateTime.now());
    }
}
