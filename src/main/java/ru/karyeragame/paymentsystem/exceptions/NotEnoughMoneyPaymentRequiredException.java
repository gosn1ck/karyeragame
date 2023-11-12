package ru.karyeragame.paymentsystem.exceptions;

public class NotEnoughMoneyPaymentRequiredException extends RuntimeException {

    public NotEnoughMoneyPaymentRequiredException(String message) {
        super(message);
    }
}
