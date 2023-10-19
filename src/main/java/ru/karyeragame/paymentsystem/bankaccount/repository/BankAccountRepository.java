package ru.karyeragame.paymentsystem.bankaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.karyeragame.paymentsystem.bankaccount.model.BankAccount;

import java.math.BigDecimal;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    @Query("SELECT ba.balance FROM BankAccount ba WHERE ba.owner.id = :ownerId AND ba.type = :type")
    BigDecimal findBalanceByOwnerIdAndType(@Param("ownerId") Long ownerId, @Param("type") String type);
}
