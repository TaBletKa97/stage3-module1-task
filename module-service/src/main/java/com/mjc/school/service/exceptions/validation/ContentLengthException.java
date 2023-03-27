package com.mjc.school.service.exceptions.validation;

public class ContentLengthException extends ValidatingDTOException {
    public ContentLengthException() {
    }

    public ContentLengthException(String message) {
        super(message);
    }

    public ContentLengthException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContentLengthException(Throwable cause) {
        super(cause);
    }
}
