package ru.karyeragame.paymentsystem.exceptions;

public class BankAccountNotFoundException extends RuntimeException {

    public BankAccountNotFoundException(String message) {
        super(message);
    }

    public BankAccountNotFoundException(String message, Long id) {
        super(String.format(message, id));
    }
}
