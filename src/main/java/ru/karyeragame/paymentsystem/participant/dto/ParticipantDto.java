package ru.karyeragame.paymentsystem.participant.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import ru.karyeragame.paymentsystem.game.model.Game;
import ru.karyeragame.paymentsystem.user.model.User;

@Builder
@Data
public class ParticipantDto {
    private User user;
    private Game game;
}