package ru.karyeragame.paymentsystem.payment.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import ru.karyeragame.paymentsystem.bankaccount.model.BankAccount;
import ru.karyeragame.paymentsystem.game.model.Game;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments", schema = "public")
@Data
@RequiredArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @Column(name = "payment_on")
    @CreationTimestamp
    private LocalDateTime paymentOn;

    @ManyToOne
    @JoinColumn(name = "account_id_from")
    private BankAccount from;

    @ManyToOne
    @JoinColumn(name = "account_id_to")
    private BankAccount to;

    private String message;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
}
