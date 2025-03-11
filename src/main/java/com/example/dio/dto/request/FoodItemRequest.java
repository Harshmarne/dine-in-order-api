package com.example.dio.dto.request;

import com.example.dio.enums.Availability;
import com.example.dio.enums.DietType;
import com.example.dio.model.CuisineType;
import com.example.dio.model.Restaurant;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class FoodItemRequest {

    @NotEmpty(message = "Username cannot be Empty")
    @NotBlank(message = "Username cannot be blank")
    private String fooditemName;
    
    private double price;

    @NotEmpty(message = "Username cannot be Empty")
    @NotBlank(message = "Username cannot be blank")
    private String description;

    private long stock;

    private Availability availability;
    private DietType dietType;
    private String cuisineType;
    private List<String> categories;

}
