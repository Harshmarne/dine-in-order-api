package com.example.dio.utility;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseBuilder {

    public static <T> ResponseEntity<ResponseStructure<T>> ok(T data,String message){
        return success(HttpStatus.OK,"OK",data);
    }

    public static <T> ResponseEntity<ResponseStructure<T>> created(T data,String message){
        return success(HttpStatus.CREATED,"CREATED",data);
    }

    /**
     * Help Creating The Success responses with
     * data including the help status code,message
     * and data it self.
     * @param status HTTP status code
     * @param message Response message
     * @param data Response data
     * @param /responseEntity of type ResponseStructure or type <T> (The type of data).
     */
    public static <T> ResponseEntity<ResponseStructure<T>> success(HttpStatus status, String message, T data) {
        ResponseStructure<T> structure = ResponseStructure.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(structure);
    }

    public static <T> ResponseEntity<ResponseStructure<T>> success(HttpStatus status, HttpHeaders headers, String message, T data) {
        ResponseStructure<T> structure = ResponseStructure.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(headers)
                .body(structure);
    }

    public static ResponseEntity<SimpleErrorResponse> notFound(String message){
        return error(HttpStatus.NOT_FOUND,"NOT FOUND");
    }

    /**
     * Help Creating The User Not Found Error responses with
     * data including the help status code,message.
     * @param status HTTP status code
     * @param message Response message
     * @param /responseEntity of type SimpleErrorResponse(Method).
     */

    public static ResponseEntity<SimpleErrorResponse> error(HttpStatus status, String message) {
        SimpleErrorResponse error = SimpleErrorResponse.builder()
                .type(status.name())
                .message(message)
                .status(status.value())
                .build();

        return ResponseEntity.status(status)
                .body(error);
    }

}

