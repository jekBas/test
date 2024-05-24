package com.example.test.exception;

public class ResourceNotFoundException extends RuntimeException {
    /**
     * ResourceNotFoundException Ctor.
     * @param message error msg
     * @param throwable throwable
     */
    public ResourceNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * ResourceNotFoundException Ctor.
     * @param message error msg
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
