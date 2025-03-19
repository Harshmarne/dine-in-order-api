package com.example.dio.repositry;

import com.example.dio.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantTableRepositry extends JpaRepository<RestaurantTable,Long> {
}

