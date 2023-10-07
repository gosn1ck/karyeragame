package ru.karyeragame.paymentsystem.participant.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.karyeragame.paymentsystem.game.model.Game;
import ru.karyeragame.paymentsystem.user.model.User;

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
    @JoinColumn(name = "user_id") //TODO: заменить на аккаунта
    private User user;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
}