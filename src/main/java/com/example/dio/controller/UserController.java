package com.example.dio.controller;

import com.example.dio.dto.request.RegistertionRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;
import com.example.dio.service.UserService;
import com.example.dio.service.impl.UserServiceImpl;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody RegistertionRequest registertionRequest) {
        UserResponse userResponse = userService.registar(registertionRequest);
        return ResponseBuilder.success(HttpStatus.CREATED, "User Created", userResponse);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable Long userId) {
        UserResponse userResponse = userService.findUserById(userId);
        return ResponseBuilder.success(HttpStatus.OK, "User Found", userResponse);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<ResponseStructure<UserResponse>> updateById(@RequestBody UserRequest userRequest,@PathVariable long userId){

        UserResponse userResponse = userService.updateUserById(userRequest,userId);
        return ResponseBuilder.success(HttpStatus.OK, "User Found", userResponse);

    }

}
