package ru.karyeragame.paymentsystem.security.recoverPassword.service;

import ru.karyeragame.paymentsystem.security.recoverPassword.dto.PasswordDtoFinal;
import ru.karyeragame.paymentsystem.security.recoverPassword.dto.PasswordDtoRequest;

public interface SecurityService {

    String validatePasswordResetToken(String token);

    void resetPassword(String userEmail);

    String showChangePasswordPage(String token);

    PasswordDtoFinal savePassword(PasswordDtoRequest passwordDtoRequest);
}
