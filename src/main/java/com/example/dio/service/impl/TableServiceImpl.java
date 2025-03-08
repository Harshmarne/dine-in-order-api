package com.example.dio.service.impl;

import com.example.dio.dto.request.RestaurantTableRequest;
import com.example.dio.dto.response.RestaurantTableResponse;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.RestaurantTableMapper;
import com.example.dio.model.*;
import com.example.dio.repositry.RestaurantRepositry;
import com.example.dio.repositry.TableRepositry;
import com.example.dio.service.TableService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TableServiceImpl implements TableService {

    private final RestaurantRepositry restaurantRepositry;
    private final RestaurantTableMapper restaurantTableMapper;
    private final TableRepositry tableRepositry;

    @Override
    public RestaurantTableResponse addTable(RestaurantTableRequest restaurantTableRequest, long restaurantId) {
        RestaurantTable restaurantTable = restaurantTableMapper.mapToRestaurantTableEntity(restaurantTableRequest);

        Restaurant restaurant = restaurantRepositry.findById(restaurantId)
                .orElseThrow(() -> new UserNotFoundByIdException("User not found , Invalid User"));

        restaurantTable.setRestaurants(restaurant);
        tableRepositry.save(restaurantTable);
        return restaurantTableMapper.mapToRestaurantTableResponse(restaurantTable);
    }
}
