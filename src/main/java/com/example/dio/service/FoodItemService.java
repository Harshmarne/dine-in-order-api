package com.example.dio.service;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.model.Category;
import com.example.dio.model.FoodItem;
import com.example.dio.model.Restaurant;

import java.util.List;

public interface FoodItemService {

    FoodItemResponse addFoodItem(FoodItemRequest foodItemRequest,long restaurantId);

    List<FoodItemResponse> findByCategories(List<String> categories);

    List<FoodItemResponse> findByRestaurant(long restaurantId);

}
