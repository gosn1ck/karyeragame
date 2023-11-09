package ru.karyeragame.paymentsystem.exceptions;

public class InvalidDataException extends RuntimeException {

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, Long id) {
        super(String.format(message, id));
    }
}
