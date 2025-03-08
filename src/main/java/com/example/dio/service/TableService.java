package com.example.dio.service;

import com.example.dio.dto.request.RestaurantTableRequest;
import com.example.dio.dto.response.RestaurantTableResponse;
import jakarta.validation.Valid;

public interface TableService {

    RestaurantTableResponse addTable(@Valid RestaurantTableRequest restaurantTableRequest, long userId);
}
