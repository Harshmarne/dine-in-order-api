package com.example.dio.service;

import com.example.dio.dto.request.RegistertionRequest;
import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;

public interface UserService {

    UserResponse registar(RegistertionRequest registertionRequest);

    UserResponse findUserById();

    UserResponse updateUserById(UserRequest userRequest);

}
