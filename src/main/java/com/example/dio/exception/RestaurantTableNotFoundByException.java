package com.example.dio.exception;

public class RestaurantTableNotFoundByException extends RuntimeException {

  private final String message;
    public RestaurantTableNotFoundByException(String message){
        this.message = message;
    }

  public String getMessage(){
    return this.message;
  }
}
