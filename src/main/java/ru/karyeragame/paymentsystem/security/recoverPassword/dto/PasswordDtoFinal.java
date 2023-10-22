package ru.karyeragame.paymentsystem.security.recoverPassword.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PasswordDtoFinal {
    String resultMessage;
    String savedPassword;
    String email;
    Long userId;
}
