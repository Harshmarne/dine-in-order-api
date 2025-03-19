package com.example.dio.mapper;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.model.Category;
import com.example.dio.model.CuisineType;
import com.example.dio.model.FoodImage;
import com.example.dio.model.FoodItem;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface FoodItemMapper {

    FoodItem mapToFoodItemEntity(FoodItemRequest foodItemRequest);

    FoodItemResponse mapToFoodItemResponse(FoodItem foodItem);

    public List<FoodItemResponse> mapToListOfFoodItemResponse(List<FoodItem> foodItems);

    default String mapToStringCuisionType(CuisineType cuisine){
        if(cuisine == null){
            return null;
        }
        return cuisine.getCuisines().toLowerCase();
    }

    default CuisineType mapToCuisineType(String cuisine){
        if(cuisine == null){
            return null;
        }
        CuisineType type = new CuisineType();
        type.setCuisines(cuisine);
        return type;
    }

    default String mapToStringCategory(Category category){
        if(category == null){
            return null;
        }
        return category.getCategory();
    }

    default Category mapToCategoryType(String category){
        if(category == null){
            return null;
        }
        Category type = new Category();
        type.setCategory(category);
        return type;
    }

    default String mapToStringFoodImage(FoodImage foodImage){
        if(foodImage == null){
            return null;
        }
        return foodImage.getImageURL();
    }

    default FoodImage mapToFoodImage(String foodImages){
        if(foodImages == null){
            return null;
        }
        FoodImage foodImage = new FoodImage();
        foodImage.setImageURL(foodImages);
        return foodImage;
    }

}
