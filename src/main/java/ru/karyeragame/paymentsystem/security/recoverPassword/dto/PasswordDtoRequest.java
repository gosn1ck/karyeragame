package ru.karyeragame.paymentsystem.security.recoverPassword.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PasswordDtoRequest {
    @NotBlank
    String token;
    @Pattern(regexp = "^[\\p{ASCII}\\p{Punct}\\p{Graph}]+$")// латиница, 0-9, видимые символы
    @NotBlank
    String newPassword;
}
