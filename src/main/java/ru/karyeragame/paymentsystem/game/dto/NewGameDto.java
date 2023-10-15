package ru.karyeragame.paymentsystem.game.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

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
    @PositiveOrZero
    private Float startBalance;
}