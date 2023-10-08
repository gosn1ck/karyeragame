package ru.karyeragame.paymentsystem.security.resetPassword.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.karyeragame.paymentsystem.security.resetPassword.model.PasswordResetToken;

public interface PasswordTokenRepository  extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
