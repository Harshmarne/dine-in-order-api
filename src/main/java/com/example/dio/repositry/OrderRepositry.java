package com.example.dio.repositry;

import com.example.dio.model.RestaurantOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositry extends JpaRepository<RestaurantOrder,Long> {
}
