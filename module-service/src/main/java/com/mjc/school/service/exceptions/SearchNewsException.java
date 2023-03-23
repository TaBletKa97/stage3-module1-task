package com.mjc.school.service.exceptions;

public class SearchNewsException extends Exception {
    public SearchNewsException() {
    }

    public SearchNewsException(String message) {
        super(message);
    }

    public SearchNewsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchNewsException(Throwable cause) {
        super(cause);
    }
}
