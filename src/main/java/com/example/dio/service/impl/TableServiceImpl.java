package com.example.dio.service.impl;

import com.example.dio.dto.request.RestaurantTableRequest;
import com.example.dio.dto.response.RestaurantTableResponse;
import com.example.dio.exception.RestaurantNotFoundByException;
import com.example.dio.mapper.RestaurantTableMapper;
import com.example.dio.model.*;
import com.example.dio.repositry.RestaurantRepositry;
import com.example.dio.repositry.RestaurantTableRepositry;
import com.example.dio.service.TableService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TableServiceImpl implements TableService {

    private final RestaurantRepositry restaurantRepositry;
    private final RestaurantTableMapper restaurantTableMapper;
    private final RestaurantTableRepositry restaurantTableRepositry;

    @Override
    public RestaurantTableResponse addTable(RestaurantTableRequest restaurantTableRequest, long restaurantId) {
        RestaurantTable restaurantTable = restaurantTableMapper.mapToRestaurantTableEntity(restaurantTableRequest);

        Restaurant restaurant = restaurantRepositry.findById(restaurantId)
                .orElseThrow(() -> new  RestaurantNotFoundByException("Restaurant Not Found By Id , Invalid Restaurant Id"));

        restaurantTable.setRestaurants(restaurant);
        restaurantTableRepositry.save(restaurantTable);
        return restaurantTableMapper.mapToRestaurantTableResponse(restaurantTable);
    }
}
