package ru.karyeragame.paymentsystem.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Long id) {
        super(String.format(message, id));
    }
}
