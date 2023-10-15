package ru.karyeragame.paymentsystem.bankaccount.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.karyeragame.paymentsystem.bankaccount.service.BankAccountService;
import ru.karyeragame.paymentsystem.enums.BankAccountType;

@RestController
@RequestMapping(path = "/bankaccount")
@RequiredArgsConstructor
@Slf4j
public class BankAccountController {

    private final BankAccountService service;

    @GetMapping("/balance")
    public Float getBalanceByUserId (@RequestParam Long userId,
                                     @RequestParam(name = "type") BankAccountType type) {
        log.info("Входящий запрос GET /game/balance?userId={}&type={}", userId, type);
        Float balance = service.getBalanceByUserId(userId, type);
        log.info("Исходящий ответ: {}", balance);
        return balance;
    }




}
