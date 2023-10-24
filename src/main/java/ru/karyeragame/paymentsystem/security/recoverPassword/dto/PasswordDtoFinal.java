package ru.karyeragame.paymentsystem.security.recoverPassword.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PasswordDtoFinal {
    String resultMessage;
    String savedPassword;
    String email;
    Long userId;
}
