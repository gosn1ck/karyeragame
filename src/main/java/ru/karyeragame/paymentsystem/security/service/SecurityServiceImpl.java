package ru.karyeragame.paymentsystem.security.service;

import ru.karyeragame.paymentsystem.security.resetPassword.model.PasswordResetToken;
import ru.karyeragame.paymentsystem.security.resetPassword.repository.PasswordTokenRepository;

import java.time.LocalDateTime;

public class SecurityServiceImpl implements SecurityService{
    PasswordTokenRepository passwordTokenRepository;

    @Override
    public String validatePasswordResetToken(String token) {
        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);

        return !isTokenFound(passToken) ? "invalidToken"
            : isTokenExpired(passToken) ? "expired"
            : null;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;  //
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        return passToken.getExpiryDate().before(LocalDateTime.now());
    }


}
