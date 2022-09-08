package com.example.libraryapi.config.exceptions;

public class  ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Object resource) {
        super(message + resource.toString());
    }
}
