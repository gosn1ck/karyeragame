package ru.karyeragame.paymentsystem.payment.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.karyeragame.paymentsystem.payment.model.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p FROM Payment p " +
            "WHERE p.from.id = ?1 AND p.to.id = ?1 AND p.game.id = ?2 ")
    List<Payment> findAllByAccountIdAndGameId(Long accountId, Long gameId, Pageable page);
}
