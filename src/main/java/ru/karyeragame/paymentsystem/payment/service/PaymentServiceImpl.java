package ru.karyeragame.paymentsystem.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karyeragame.paymentsystem.bankaccount.model.BankAccount;
import ru.karyeragame.paymentsystem.bankaccount.service.BankAccountService;
import ru.karyeragame.paymentsystem.exceptions.NotEnoughMoneyPaymentRequiredException;
import ru.karyeragame.paymentsystem.game.service.GameService;
import ru.karyeragame.paymentsystem.payment.dto.NewPaymentDto;
import ru.karyeragame.paymentsystem.payment.dto.PaymentDto;
import ru.karyeragame.paymentsystem.payment.mapper.PaymentMapper;
import ru.karyeragame.paymentsystem.payment.model.Payment;
import ru.karyeragame.paymentsystem.payment.repository.PaymentRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final BankAccountService bankAccountService;
    private final GameService gameService;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;

    @Override
    @Transactional
    public PaymentDto createPayment(NewPaymentDto dto) {
        BankAccount from = bankAccountService.getBankAccountEntity(dto.getAccountIdFrom()); //откуда
        accountBalanceCheck(from, dto.getAmount());  // Проверка баланса
        BankAccount to = bankAccountService.getBankAccountEntity(dto.getAccountIdTo());  //куда
        gameService.getGameEntity(dto.getGameId()); // Проверка, есть ли игра

        Payment payment = mapper.toEntity(dto); //создаем платеж

        bankAccountService.updateBankAccount(from.getId(), from.getBalance().subtract(dto.getAmount())); //вычитаем из from сумму платежа
        bankAccountService.updateBankAccount(to.getId(), to.getBalance().add(dto.getAmount())); //добавляем к to сумму платежа

        return mapper.toPaymentDto(paymentRepository.save(payment));
    }

    @Override
    public List<PaymentDto> getStatementByAccountId(Long accountId, Long gameId, int from, int size) {
        Pageable page = PageRequest.of(from, size, Sort.by("paymentOn"));
        List<Payment> paymentList = paymentRepository.findAllByAccountIdAndGameId(accountId, gameId, page);
        return mapper.toPaymentDtoList(paymentList);
    }

    private void accountBalanceCheck(BankAccount from, BigDecimal sumForPayment) {
        if (sumForPayment.equals(from.getBalance())) {
            log.error("Банковский счет с id {} не располагает суммой {}", from.getId(), sumForPayment);
            throw new NotEnoughMoneyPaymentRequiredException(String.format("Банковский счет с id %d не располагает суммой %.2f",
                    from.getId(), sumForPayment));
        }
    }
}
