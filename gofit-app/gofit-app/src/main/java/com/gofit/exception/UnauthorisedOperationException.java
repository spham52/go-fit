package com.gofit.exception;

public class UnauthorisedOperationException extends RuntimeException {
    public UnauthorisedOperationException(String message) {
        super(message);
    }
}
