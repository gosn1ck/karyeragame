package ru.karyeragame.paymentsystem.bankaccount.service;

import ru.karyeragame.paymentsystem.bankaccount.model.BankAccount;
import ru.karyeragame.paymentsystem.enums.bank_account.BankAccountType;

import java.math.BigDecimal;

public interface BankAccountService {

    BigDecimal getBalanceByUserId(Long userId, BankAccountType type);

    BankAccount getBankAccountEntity(Long bankAccountId);

    void updateBankAccount(Long id, BigDecimal sum);
}
