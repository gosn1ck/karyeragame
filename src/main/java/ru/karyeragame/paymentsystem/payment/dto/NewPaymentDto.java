package ru.karyeragame.paymentsystem.payment.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class NewPaymentDto {

    @NotNull
    @Digits(integer = 10, fraction = 2) //10 знаков до точки и 2 знака после запятой
    private BigDecimal amount;

    @NotNull
    private Long accountIdFrom;

    @NotNull
    private Long accountIdTo;

    @NotNull
    @Size(min = 5, max = 200, message = "Назначение платежа не может быть меньше 5 символов и не больше 200")
    private String message;

    @NotNull
    private Long gameId;
}
