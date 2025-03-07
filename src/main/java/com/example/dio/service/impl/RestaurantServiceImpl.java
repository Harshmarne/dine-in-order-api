package com.example.dio.service.impl;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.RestaurantMapper;
import com.example.dio.model.Admin;
import com.example.dio.model.CuisineType;
import com.example.dio.model.Restaurant;
import com.example.dio.model.User;
import com.example.dio.repositry.CuisineTypeRepositry;
import com.example.dio.repositry.RestaurantRepositry;
import com.example.dio.repositry.UserRepositry;
import com.example.dio.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantMapper restaurantMapper;
    private final RestaurantRepositry restaurantRepositry;
    private final CuisineTypeRepositry cuisineTypeRepositry;
    private final UserRepositry userRepositry;

    @Override
    public RestaurantResponse restaurant(RestaurantRequest restaurantRequest, long userId) {
        User user = userRepositry.findById(userId).orElseThrow(() -> new UserNotFoundByIdException("User not found , Invalid User"));
        if(user instanceof Admin admin){
            Restaurant restaurant = restaurantMapper.mapToRestaurantEntity(restaurantRequest);

            List<CuisineType> cuisineTypes = this.createNotExistingCuisineTypes(restaurant.getCuisineTypes());
            restaurant.setCuisineTypes(cuisineTypes);
            restaurant.setAdmin(admin);

            restaurantRepositry.save(restaurant);

            return restaurantMapper.mapToRestaurantResponse(restaurant);

        }
        else {
            throw new UserNotFoundByIdException("Admin Not Valid");
        }
    }


    private List<CuisineType> createNotExistingCuisineTypes(List<CuisineType> cuisineTypes) {
        return cuisineTypes.stream()
                .map(type -> cuisineTypeRepositry.findById(type.getCuisines())  // Assuming getId() is the identifier.
                        .orElseGet(() -> {
                            type.setCuisines(type.getCuisines().toLowerCase());
                            return cuisineTypeRepositry.save(type);  // Save the modified CuisineType and return it.
                        }))
                .toList();
    }

}
