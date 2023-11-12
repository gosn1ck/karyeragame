package ru.karyeragame.paymentsystem.exceptions;

public class LoadDataException extends RuntimeException {

    public LoadDataException(String message) {
        super(message);
    }

    public LoadDataException(String message, Long id) {
        super(String.format(message, id));
    }

    public LoadDataException(String message, String param) {
        super(String.format(message, param));
    }

}
