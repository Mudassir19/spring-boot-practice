package com.practice.boot.exception;

public class RecordExist extends RuntimeException {

    public RecordExist(String message) {
        super(message);
        this.message = message;
    }

    private String message;
}
