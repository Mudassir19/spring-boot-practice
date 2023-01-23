package com.practice.boot.exception;

public class TokenExpiredException extends RuntimeException {

    private String message;

    public TokenExpiredException(String message) {
        super(message);
        this.message = message;
    }

    public TokenExpiredException() {
    }
}
