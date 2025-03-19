package com.example.dio.exception;

public class NoBillFoundException extends RuntimeException {

    private final String message;
    public NoBillFoundException(String message) {
        this.message = message;
    }

  public String getMessage(){
    return this.message;
  }
}
