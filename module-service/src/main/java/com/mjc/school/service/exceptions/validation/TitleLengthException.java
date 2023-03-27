package com.mjc.school.service.exceptions.validation;

public class TitleLengthException extends ValidatingDTOException {
    public TitleLengthException() {
    }

    public TitleLengthException(String message) {
        super(message);
    }

    public TitleLengthException(String message, Throwable cause) {
        super(message, cause);
    }

    public TitleLengthException(Throwable cause) {
        super(cause);
    }
}
