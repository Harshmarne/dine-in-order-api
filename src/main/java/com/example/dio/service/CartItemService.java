package com.example.dio.service;


import com.example.dio.dto.response.CartItemResponse;

public interface CartItemService {

    CartItemResponse CreateCartItem(long tableId,long foodItemId,int quantity);

    public CartItemResponse updateQuantity(long cartId, int quantity);
}
