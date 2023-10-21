package ru.karyeragame.paymentsystem.security.recoverPassword.dto;

import lombok.Getter;

@Getter
public class PasswordDtoRequest {
    String token;
    // прописать аннотации с требованиями к новому паспорту
    String newPassword;
}
