package com.mjc.school.service.exceptions.validation;

public class ValidatingDTOException extends Exception {
    public ValidatingDTOException() {
    }

    public ValidatingDTOException(String message) {
        super(message);
    }

    public ValidatingDTOException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatingDTOException(Throwable cause) {
        super(cause);
    }
}
