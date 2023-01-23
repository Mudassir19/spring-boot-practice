package com.practice.boot.exception;

public class RecordNotFound extends RuntimeException {

    public RecordNotFound(String message) {
        super(message);
        this.message = message;
    }

    private String message;
}
