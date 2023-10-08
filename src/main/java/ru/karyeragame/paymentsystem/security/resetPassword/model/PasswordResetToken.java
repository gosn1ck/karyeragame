package ru.karyeragame.paymentsystem.security.resetPassword.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.karyeragame.paymentsystem.user.model.User;

import java.util.Date;

@Entity
@Getter
@EqualsAndHashCode
@ToString
public class PasswordResetToken {                       // токен сброса пароля
    private static final int EXPIRATION = 60 * 24;      // срок действия ссылки для восстановления пароля

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private Date expiryDate;

    public PasswordResetToken(User user, String token) {
    }
}
