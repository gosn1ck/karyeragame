package ru.karyeragame.paymentsystem.bankaccount.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import ru.karyeragame.paymentsystem.enums.BankAccountStatus;
import ru.karyeragame.paymentsystem.enums.BankAccountType;
import ru.karyeragame.paymentsystem.user.model.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "bank_accounts", schema = "public")
@Getter
@Setter
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "owner_id")
    private User owner;

    private Float balance;

    @Enumerated(EnumType.STRING)
    private BankAccountStatus status;

    @Enumerated(EnumType.STRING)
    private BankAccountType type;

    @CreationTimestamp
    @Column(name = "created_on")
    private LocalDateTime createdOn;
}
