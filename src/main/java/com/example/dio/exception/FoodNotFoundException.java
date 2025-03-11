package com.example.dio.exception;

public class FoodNotFoundException extends RuntimeException{
    private final String message;
    public FoodNotFoundException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
