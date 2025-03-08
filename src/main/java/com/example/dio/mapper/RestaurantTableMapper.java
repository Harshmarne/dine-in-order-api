package com.example.dio.mapper;

import com.example.dio.dto.request.RestaurantTableRequest;
import com.example.dio.dto.response.RestaurantTableResponse;
import com.example.dio.model.RestaurantTable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantTableMapper {

    RestaurantTable mapToRestaurantTableEntity(RestaurantTableRequest restaurantTableRequest);

    RestaurantTableResponse mapToRestaurantTableResponse(RestaurantTable restaurantTable);
}
