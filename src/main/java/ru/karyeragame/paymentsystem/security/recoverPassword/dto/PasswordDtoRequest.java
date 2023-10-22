package ru.karyeragame.paymentsystem.security.recoverPassword.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PasswordDtoRequest {
    String token;
    // прописать аннотации с требованиями к новому паспорту
    @NotBlank
    String newPassword;
}
