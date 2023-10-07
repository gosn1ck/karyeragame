package ru.karyeragame.paymentsystem.game.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.karyeragame.paymentsystem.enums.GameStatus;
import ru.karyeragame.paymentsystem.user.model.User;

import java.time.LocalDateTime;

@Builder
@Data
public class NewGameDto {
    @NotBlank
    @Size(max = 100)
    private String name;
    @Size(max = 255)
    private String comment;
    @NotNull
    private Long createdBy;
    @NotNull
    @Size(max = 20)
    private GameStatus status;
    @NotNull
    @PositiveOrZero
    private Integer startBalance;
}