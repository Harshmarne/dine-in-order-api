package com.example.dio.exception;

public class EligelActivetyException extends RuntimeException {
    
    private String message;

    public EligelActivetyException(String message) {
        super(message);
    }
}
