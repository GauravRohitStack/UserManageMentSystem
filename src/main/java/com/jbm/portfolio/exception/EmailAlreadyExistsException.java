package com.jbm.portfolio.exception;

@SuppressWarnings("serial")
public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
