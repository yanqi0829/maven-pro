package com.mycom.mvnbook.email.exception;

public class AccountEmailException extends RuntimeException {
    public AccountEmailException(String message) {
        super(message);
    }

    public AccountEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
