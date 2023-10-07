package ru.karyeragame.paymentsystem.participant.dto;

import lombok.Builder;
import lombok.Data;
import ru.karyeragame.paymentsystem.game.model.Game;
import ru.karyeragame.paymentsystem.user.model.User;

@Builder
@Data
public class ParticipantDto {
    private User user;
    private Game game;
}