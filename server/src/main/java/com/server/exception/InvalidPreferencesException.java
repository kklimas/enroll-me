package com.server.exception;

public class InvalidPreferencesException extends RuntimeException {
    public InvalidPreferencesException(String message) {
        super(message);
    }
}
