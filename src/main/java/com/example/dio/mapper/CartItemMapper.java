package com.example.dio.mapper;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.model.CartItem;
import com.example.dio.model.Category;
import com.example.dio.model.CuisineType;
import com.example.dio.model.FoodImage;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CartItemMapper {

    CartItemResponse mapToCartItemResponse(CartItem cartItem);

    default String mapToString(Category value) {
        if(value == null) {
            return null;
        }
        else return value.getCategory().toLowerCase();
    }

    default String mapToString(CuisineType cuisine) {
        if(cuisine == null) {
            return null;
        }
        else return cuisine.getCuisines().toLowerCase();
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
