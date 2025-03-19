package com.example.dio.repositry;

import com.example.dio.model.FoodImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepositry extends JpaRepository<FoodImage,Long> {
}
