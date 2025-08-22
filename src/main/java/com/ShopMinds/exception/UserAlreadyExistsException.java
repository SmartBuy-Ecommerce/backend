package com.ShopMinds.exception;

public class GlobalExceptionHandler extends RuntimeException {

    public GlobalExceptionHandler() {
        super(); // Default constructor
    }

    public GlobalExceptionHandler(String message) {
        super(message); // Constructor with custom message
    }

    public GlobalExceptionHandler(String message, Throwable cause) {
        super(message, cause); // Constructor with message and root cause
    }
}