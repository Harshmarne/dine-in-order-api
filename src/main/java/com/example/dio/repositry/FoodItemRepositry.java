package com.example.dio.repositry;

import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.model.Category;
import com.example.dio.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepositry extends JpaRepository<FoodItem,Long> {

    @Query("SELECT f FROM FoodItem f " +
            "INNER JOIN f.categories c " +
            "WHERE c.category IN :categories " +
            "GROUP BY f.fooditemId " +
            "HAVING COUNT(DISTINCT c.category) = :categoryCount")
    List<FoodItem> findFoodItemByCategory(@Param("categories") List<String> categories, @Param("categoryCount") int categoryCount);
}
