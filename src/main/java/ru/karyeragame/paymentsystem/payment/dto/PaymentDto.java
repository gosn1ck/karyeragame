package ru.karyeragame.paymentsystem.payment.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Builder
@Data
public class PaymentDto {

    private Long id;

    private BigDecimal amount;

    private LocalDateTime paymentOn;

    private Long accountIdFrom;

    private Long accountIdTo;

    private String message;

    private Long gameId;
}
