package ru.karyeragame.paymentsystem.participant.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import ru.karyeragame.paymentsystem.enums.GameStatus;
import ru.karyeragame.paymentsystem.game.model.Game;
import ru.karyeragame.paymentsystem.user.model.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "participants", schema = "public")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Id
    private User user;
    @ManyToOne
    @JoinColumn(name = "game_id")
    @Id
    private Game game;
}