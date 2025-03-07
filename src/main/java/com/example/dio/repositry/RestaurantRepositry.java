package com.example.dio.repositry;

import com.example.dio.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepositry extends JpaRepository<Restaurant,Long> {
}
