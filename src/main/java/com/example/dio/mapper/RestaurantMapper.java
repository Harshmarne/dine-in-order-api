package com.example.dio.mapper;


import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.model.CuisineType;
import com.example.dio.model.Restaurant;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface RestaurantMapper {

    Restaurant mapToRestaurantEntity(RestaurantRequest restaurantRequest);

    RestaurantResponse mapToRestaurantResponse(Restaurant restaurant);

    default String mapToStringCuisionType(CuisineType cuisineType){
        if(cuisineType == null){
            return null;
        }
        return cuisineType.getCuisines();
    }

    default CuisineType mapToCuisineType(String cuisineType){
        if(cuisineType == null){
            return null;
        }
        CuisineType cuisine = new CuisineType();
        cuisine.setCuisines(cuisineType);
        return cuisine;
    }

}
