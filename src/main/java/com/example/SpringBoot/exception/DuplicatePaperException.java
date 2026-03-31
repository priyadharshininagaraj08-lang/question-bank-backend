package com.example.SpringBoot.exception;

public class DuplicatePaperException extends RuntimeException {

    public DuplicatePaperException(String message) {
        super(message);
    }
}