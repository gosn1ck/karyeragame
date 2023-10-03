package ru.karyeragame.paymentsystem.bankaccount.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karyeragame.paymentsystem.bankaccount.repository.BankAccountRepository;
import ru.karyeragame.paymentsystem.enums.BankAccountType;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository repository;

    @Override
    public Float getBalanceByUserId(Long ownerId, BankAccountType type) {
        return repository.findBalanceByOwnerIdAndType(ownerId, String.valueOf(type));
    }
}
