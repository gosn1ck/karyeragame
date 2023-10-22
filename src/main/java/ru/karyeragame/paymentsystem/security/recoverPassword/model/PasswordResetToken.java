package ru.karyeragame.paymentsystem.security.recoverPassword.model;

import jakarta.persistence.*;
import lombok.*;
import ru.karyeragame.paymentsystem.user.model.User;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Password_Reset_Token", schema = "public")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PasswordResetToken {                       // токен сброса пароля
    private static final int EXPIRATION = 60 * 24;      // срок действия ссылки для восстановления пароля
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    public PasswordResetToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expiryDate = LocalDateTime.now().plusMinutes(EXPIRATION);
    }
}
