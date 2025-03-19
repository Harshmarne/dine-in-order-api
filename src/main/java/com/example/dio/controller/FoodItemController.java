package com.example.dio.controller;


import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.service.FoodItemService;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.ResponseStructure;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class FoodItemController {

    private final FoodItemService foodItemService;

    @PostMapping("/fooditem/{restaurantId}")
    public ResponseEntity<ResponseStructure<FoodItemResponse>> foodItem(@Valid @RequestBody FoodItemRequest foodItemRequest, @PathVariable long restaurantId) {
        FoodItemResponse foodItemResponse = foodItemService.addFoodItem(foodItemRequest, restaurantId);
        return ResponseBuilder.created(foodItemResponse, "FoodItem Added SuccsessFully");
    }

    @GetMapping("/items/categories")
    public ResponseEntity<ResponseStructure<List<FoodItemResponse>>> findByCategories(@RequestParam List<String> categories) {
        return ResponseBuilder.ok(foodItemService.findByCategories(categories), "Food item List found according categories");
    }

    @GetMapping("/restaurant/{restaurantId}/food-items")
    public ResponseEntity<ResponseStructure<List<FoodItemResponse>>> findByRestaurantId(@PathVariable long restaurantId){
        return ResponseBuilder.ok(foodItemService.findByRestaurant(restaurantId),"Food Item is Not Available");
    }
}

