package ru.karyeragame.paymentsystem.bank_account.service;

import ru.karyeragame.paymentsystem.bank_account.model.BankAccount;
import ru.karyeragame.paymentsystem.bank_account.model.BankAccountType;

import java.math.BigDecimal;

public interface BankAccountService {

    BigDecimal getBalanceByUserId(Long userId, BankAccountType type);

    BankAccount getBankAccountEntity(Long bankAccountId);

    void updateBankAccount(Long id, BigDecimal sum);
}
