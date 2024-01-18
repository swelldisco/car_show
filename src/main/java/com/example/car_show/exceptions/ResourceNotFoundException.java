package com.example.car_show.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private String message;

    public ResourceNotFoundException() {
        message = "Resource not found";
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public String getMessage() {
        return message;
    }
}
