package com.example.dio.dto.response;

import com.example.dio.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponse {

    private int quantity;

    private double totalPrice;

    private FoodItemResponse foodItem;

    private OrderStatus isOrdered;

}
