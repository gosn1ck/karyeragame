package ru.karyeragame.paymentsystem.game.dto;

import lombok.Builder;
import lombok.Data;
import ru.karyeragame.paymentsystem.enums.GameStatus;

@Builder
@Data
public class UpdateGameDto {
    private String name;
    private String comment;
    private GameStatus status;
    private Float startBalance;
}