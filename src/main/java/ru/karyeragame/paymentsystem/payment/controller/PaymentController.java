package ru.karyeragame.paymentsystem.payment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.karyeragame.paymentsystem.payment.dto.NewPaymentDto;
import ru.karyeragame.paymentsystem.payment.dto.PaymentDto;
import ru.karyeragame.paymentsystem.payment.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    public final PaymentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentDto createPayment(@RequestBody NewPaymentDto dto) {
        log.info("Входящий запрос POST /payments: {}", dto);
        PaymentDto response = service.createPayment(dto);
        log.info("Исходящий ответ: {}", response);
        return response;
    }

    @GetMapping("/statement/account/{accountId}/game/{gameId}")
    public List<PaymentDto> getStatement(
            @PathVariable Long accountId,
            @PathVariable Long gameId,
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "10") int size) {
        log.info("Входящий запрос GET /payments/statement/account/{}/game/{}?from={}&size={}",
                accountId, gameId, from, size);
        List<PaymentDto> resultList = service.getStatementByAccountId(accountId, gameId, from, size);
        log.info("Исходящий ответ: {}", resultList);
        return resultList;
    }
}
