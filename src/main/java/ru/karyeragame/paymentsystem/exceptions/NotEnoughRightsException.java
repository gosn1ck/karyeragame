package ru.karyeragame.paymentsystem.exceptions;

public class NotEnoughRightsException extends RuntimeException {
    public NotEnoughRightsException(String message) {
        super(message);
    }
}
