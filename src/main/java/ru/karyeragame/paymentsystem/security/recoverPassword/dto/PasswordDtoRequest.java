package ru.karyeragame.paymentsystem.security.recoverPassword.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PasswordDtoRequest {
    @NotBlank
    String token;
    @NotBlank
    String newPassword;
}
