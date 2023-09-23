package ru.karyeragame.paymentsystem.exceptions;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({NotFoundException.class, EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(final Exception e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND,
                LocalDateTime.now(),
                e.getMessage(),
                e.getStackTrace());
    }

    @Getter
    private static class ErrorResponse {
        String message;
        HttpStatus status;
        LocalDateTime timestamp;
        StackTraceElement[] stackTraceElements;

        @Override
        public String toString() {
            return "ErrorResponse{" +
                    "status=" + status +
                    ", timestamp=" + timestamp +
                    ", message='" + message +
                    ", stackTrace='" + stackTraceElements +
                    '}';
        }

        ErrorResponse(HttpStatus status,
                      LocalDateTime timestamp,
                      String message,
                      StackTraceElement[] stackTraceElements) {
            this.message = message;
            this.stackTraceElements = stackTraceElements;
            this.status = status;
            this.timestamp = timestamp;
        }
    }
}
