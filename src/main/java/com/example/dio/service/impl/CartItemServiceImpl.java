package com.example.dio.service.impl;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.enums.OrderStatus;
import com.example.dio.exception.FoodNotFoundException;
import com.example.dio.mapper.CartItemMapper;
import com.example.dio.model.CartItem;
import com.example.dio.model.FoodItem;
import com.example.dio.model.RestaurantTable;
import com.example.dio.repositry.CartItemRepositry;
import com.example.dio.repositry.FoodItemRepositry;
import com.example.dio.repositry.RestaurantTableRepositry;
import com.example.dio.service.CartItemService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemMapper cartItemMapper;
    private final RestaurantTableRepositry restaurantTableRepositry;
    private final FoodItemRepositry foodItemRepositry;
    private final CartItemRepositry cartItemRepositry;

    /**
     * @param tableId
     * @param foodItemId
     * @param quantity
     * @return
     */
    @Override
    public CartItemResponse CreateCartItem(long tableId, long foodItemId, int quantity) {

        RestaurantTable restaurantTable = restaurantTableRepositry.findById(tableId)
                .orElseThrow(() -> new InvalidParameterException("Table Not Found !!"));

        FoodItem foodItem = foodItemRepositry.findById(foodItemId)
                .orElseThrow(() -> new FoodNotFoundException("Food Not Found To Add In Cart"));

        CartItem cartItem = cartItemRepositry.save(
                getCartItem(quantity, foodItem, restaurantTable));

        cartItem.setFoodItem(foodItem);
        cartItem.setRestaurantTable(restaurantTable);
        return cartItemMapper.mapToCartItemResponse(cartItem);

    }

    /**
     * @param cartId
     * @param quantity
     * @return
     */
    @Override
    public CartItemResponse updateQuantity(long cartId, int quantity) {
        CartItem cartItem = cartItemRepositry.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart item with ID " + cartId + " not found"));

        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(cartItem.getFoodItem().getPrice() * quantity);

        return cartItemMapper.mapToCartItemResponse(
                cartItemRepositry.save(cartItem)
        );
    }

    private static CartItem getCartItem(int quantity, FoodItem foodItem, RestaurantTable restaurantTable) {
        CartItem cartItem = new CartItem();
        cartItem.setFoodItem(foodItem);
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(foodItem.getPrice() * cartItem.getQuantity());
        cartItem.setRestaurantTable(restaurantTable);
        cartItem.setIsOrdered(OrderStatus.NOT_ORDERED);
        return cartItem;
    }
}
