package com.example.dio.service;

import com.example.dio.dto.request.RegistertionRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;

/**
 * UserService interface inside this we have declared a some method
 * this interface implement to service class .
 */

public interface UserService {

    UserResponse registar(RegistertionRequest registertionRequest);

    UserResponse findUserById(long userId);


    UserResponse updateUserById(UserRequest userRequest, long userId);
}
