package ru.karyeragame.authservice.usercredential;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Response DTO for {@link UserCredential}
 */
@Getter
@AllArgsConstructor
public class ResponseUserCredDto {
    @Schema(example = "2653", description = "")
    private final Long id;
    @Schema(example = "user@gmail.com", description = "")
    private final String email;
}
