package ru.karyeragame.paymentsystem.bank_account.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.karyeragame.paymentsystem.bank_account.model.BankAccountType;
import ru.karyeragame.paymentsystem.bank_account.service.BankAccountService;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/bank-accounts")
@RequiredArgsConstructor
@Slf4j
public class BankAccountController {

    private final BankAccountService service;

    @GetMapping("/{userId}/balance")
    public BigDecimal getBalanceByUserId(@PathVariable Long userId,
                                         @RequestParam(name = "type") BankAccountType type) {
        log.info("Входящий запрос GET /bank-accounts/{}/balance?type={}", userId, type);
        BigDecimal balance = service.getBalanceByUserId(userId, type);
        log.info("Исходящий ответ: {}", balance);
        return balance;
    }
}
