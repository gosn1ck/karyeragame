package ru.karyeragame.authservice.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ErrorResponse {
    @Schema(example = "HTTP_STATUS", description = "")
    HttpStatus status;
    @Schema(example = "Human readable error message", description = "")
    String message;
    @Schema(example = "2020-05-30T22:33:19.953567014", description = "")
    LocalDateTime timestamp;

}
