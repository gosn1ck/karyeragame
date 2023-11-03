package ru.karyeragame.paymentsystem.game.dto;

import lombok.Builder;
import lombok.Data;
import ru.karyeragame.paymentsystem.enums.game.GameStatus;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
public class GameDto {

    private Long id;

    private String name;

    private String comment;

    private LocalDateTime createdOn;

    private Long createdById;

    private GameStatus status;

    private Float startBalance;

    private Set<Long> participantsIds;
}