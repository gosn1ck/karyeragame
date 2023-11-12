package ru.karyeragame.paymentsystem.game.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import ru.karyeragame.paymentsystem.user.model.User;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "games", schema = "public")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String comment;

    @Column(name = "created_on", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdOn;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    @Column(name = "start_balance", nullable = false)
    private Float startBalance;

    @ManyToMany
    @JoinTable(
            name = "participants",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<User> participants;
}