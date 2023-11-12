package ru.karyeragame.paymentsystem.exceptions;

public class DataConflictException extends RuntimeException {

    public DataConflictException(String message) {
        super(message);
    }

    public DataConflictException(String message, Long id) {
        super(String.format(message, id));
    }
}
