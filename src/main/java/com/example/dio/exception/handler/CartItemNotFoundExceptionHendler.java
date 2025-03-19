package com.example.dio.exception.handler;

import com.example.dio.exception.CartItemsNotFoundException;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.SimpleErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CartItemNotFoundExceptionHendler {


    @ExceptionHandler
    public ResponseEntity<SimpleErrorResponse> CartNotFoundException(CartItemsNotFoundException e){
        return ResponseBuilder.notFound(e.getMessage());
    }
}
