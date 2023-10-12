package ru.karyeragame.paymentsystem.security.recoverPassword.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.karyeragame.paymentsystem.security.recoverPassword.model.PasswordResetToken;

public interface PasswordTokenRepository  extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
