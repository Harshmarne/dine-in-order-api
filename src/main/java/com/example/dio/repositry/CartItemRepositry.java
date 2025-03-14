package com.example.dio.repositry;

import com.example.dio.enums.OrderStatus;
import com.example.dio.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepositry extends JpaRepository<CartItem,Long> {

    List<CartItem> findByIsOrderedAndRestaurantTable_tableid(OrderStatus orderStatus, Long restaurantTableId);
}
