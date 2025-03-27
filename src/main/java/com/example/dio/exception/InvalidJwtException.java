package com.example.dio.exception;

public class InvalidJwtException extends RuntimeException {
    private String message;
    public InvalidJwtException(String message) {
        super(message);
    }
}
