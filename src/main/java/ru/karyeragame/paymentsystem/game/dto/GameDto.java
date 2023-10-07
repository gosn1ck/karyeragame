package ru.karyeragame.paymentsystem.game.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import ru.karyeragame.paymentsystem.enums.GameStatus;
import ru.karyeragame.paymentsystem.user.model.User;

import java.time.LocalDateTime;

@Builder
@Data
public class GameDto {
    private Long id;
    private String name;
    private String comment;
    private LocalDateTime createdOn;
    private User createdBy;
    private GameStatus status;
    private Integer startBalance;
}