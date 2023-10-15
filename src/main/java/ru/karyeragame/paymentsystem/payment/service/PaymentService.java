package ru.karyeragame.paymentsystem.payment.service;

import ru.karyeragame.paymentsystem.payment.dto.NewPaymentDto;
import ru.karyeragame.paymentsystem.payment.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    PaymentDto createPayment(NewPaymentDto dto);

    List<PaymentDto> getStatementByAccountId(Long accountId, Long gameId, int from, int size);
}
