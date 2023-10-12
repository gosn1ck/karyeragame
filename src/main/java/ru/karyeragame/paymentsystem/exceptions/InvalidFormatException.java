package ru.karyeragame.paymentsystem.exceptions;

public class InvalidFormatException extends RuntimeException {

    public InvalidFormatException(String message) {
        super(message);
    }

    public InvalidFormatException(String message, Long id) {
        super(String.format(message, id));
    }
}
