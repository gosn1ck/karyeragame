package ru.karyeragame.paymentsystem.security.service;

import org.springframework.stereotype.Service;
import ru.karyeragame.paymentsystem.security.resetPassword.model.PasswordResetToken;
import ru.karyeragame.paymentsystem.security.resetPassword.repository.PasswordTokenRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
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
        return passToken.getExpiryDate().toInstant().isBefore(Instant.now());
    }


}
