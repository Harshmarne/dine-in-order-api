package com.example.dio.service.impl;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.enums.Availability;
import com.example.dio.exception.FoodNotFoundException;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.FoodItemMapper;
import com.example.dio.model.Category;
import com.example.dio.model.CuisineType;
import com.example.dio.model.FoodItem;
import com.example.dio.model.Restaurant;
import com.example.dio.repositry.CategoryRepositry;
import com.example.dio.repositry.CuisineTypeRepositry;
import com.example.dio.repositry.FoodItemRepositry;
import com.example.dio.repositry.RestaurantRepositry;
import com.example.dio.service.FoodItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor

public class FoodItemServiceImpl implements FoodItemService {

    private final RestaurantRepositry restaurantRepositry;
    private final FoodItemRepositry foodItemRepositry;
    private final FoodItemMapper foodItemMapper;
    private final CuisineTypeRepositry cuisineTypeRepositry;
    private final CategoryRepositry categoryRepositry;

    @Override
    @Transactional
    public FoodItemResponse addFoodItem(FoodItemRequest foodItemRequest, long restaurantId) {
        FoodItem foodItem = foodItemMapper.mapToFoodItemEntity(foodItemRequest);

        Restaurant restaurant = restaurantRepositry.findById(restaurantId)
                .orElseThrow(()-> new UserNotFoundByIdException("User Not Found"));

        cuisineTypeRepositry.findById(foodItem.getCuisineType().getCuisines())
                .orElseGet(() -> {
                    CuisineType cuisineType = cuisineTypeRepositry.save(foodItem.getCuisineType());
                    restaurant.getCuisineTypes().add(cuisineType);
                    restaurantRepositry.save(restaurant);
                    return cuisineType;
                });

        if(foodItem.getStock() > 0){
            foodItem.setAvailability(Availability.AVAILABILITY);
        }
        else{
            foodItem.setAvailability(Availability.OUT_OF_STOCK);
        }

        foodItem.setCategories(this.createNotExistingCategory(foodItem.getCategories()));

        foodItem.setRestaurant(restaurant);
        foodItem.setCuisineType(foodItem.getCuisineType());

        foodItemRepositry.save(foodItem);
        return foodItemMapper.mapToFoodItemResponse(foodItem);
    }

    /**
     * @param categories
     * @return
     */
    @Override
    public List<FoodItemResponse> findByCategories(List<String> categories) {
        if(categories.isEmpty()){
            throw new FoodNotFoundException("No food with this categories");
        }
        else{
            List<FoodItemResponse> foodItemList = foodItemMapper.mapToListOfFoodItemResponse(
                    foodItemRepositry.findFoodItemByCategory(
                            categories.stream().distinct().toList(), categories.size()));
            if(foodItemList.isEmpty()){
                throw new FoodNotFoundException("No food with this categories");
            }
            else {
                return foodItemList;
            }
        }
    }

    private List<Category> createNotExistingCategory(List<Category> categories) {
        return categories.stream()
                .map(type -> categoryRepositry.findById(type.getCategory())  // Assuming getId() is the identifier.
                        .orElseGet(() -> {
                            type.setCategory(type.getCategory().toLowerCase());
                            return categoryRepositry.save(type);  // Save the modified CuisineType and return it.
                        }))
                .toList();
    }
}
