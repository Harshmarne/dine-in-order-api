package com.example.dio.exception;

public class CartItemsNotFoundException extends RuntimeException {

    private final String message;

    public CartItemsNotFoundException(String message) {
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
}
