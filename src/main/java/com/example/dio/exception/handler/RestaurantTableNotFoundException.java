package com.example.dio.exception.handler;

import com.example.dio.exception.FoodNotFoundException;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.SimpleErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestaurantTableNotFoundException {
    @ExceptionHandler
    public ResponseEntity<SimpleErrorResponse> RestaurantTableNotFoundException(FoodNotFoundException e){
        return ResponseBuilder.notFound(e.getMessage());
    }
}
