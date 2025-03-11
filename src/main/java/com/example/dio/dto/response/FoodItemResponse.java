package com.example.dio.dto.response;

import com.example.dio.enums.Availability;
import com.example.dio.enums.DietType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class FoodItemResponse {

    private String fooditemName;
    private double price;
    private String description;
    private long stock;
    private Availability availability;
    private DietType dietType;
    private String cuisineType;
    private LocalDate createdAt;
    private LocalDate lastModifiedAt;
    private List<String> categories;
}
