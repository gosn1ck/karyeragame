package ru.karyeragame.authservice.usercredential;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Response DTO for {@link UserCredential}
 */
@Getter
@AllArgsConstructor
public class ResponseUserCredDto {
    private final Long id;
    private final String email;
}
