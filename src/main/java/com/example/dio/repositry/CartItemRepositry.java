package com.example.dio.repositry;

import com.example.dio.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepositry extends JpaRepository<CartItem,Long> {
}
