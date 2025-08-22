package com.ShopMinds.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super(); // Default constructor
    }

    public UserAlreadyExistsException(String message) {
        super(message); // Constructor with custom message
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause); // Constructor with message and root cause
    }
}