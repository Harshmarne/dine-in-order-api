package com.example.dio.exception;

public class RestaurantNotFoundByException extends RuntimeException {
    private final String message;
    public RestaurantNotFoundByException(String message) {
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
}
