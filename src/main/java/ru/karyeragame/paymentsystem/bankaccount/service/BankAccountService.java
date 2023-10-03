package ru.karyeragame.paymentsystem.bankaccount.service;

import ru.karyeragame.paymentsystem.enums.BankAccountType;

public interface BankAccountService {
    Float getBalanceByUserId(Long userId, BankAccountType type);
}
