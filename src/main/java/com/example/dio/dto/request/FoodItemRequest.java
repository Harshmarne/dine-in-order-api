package com.example.dio.dto.request;

import com.example.dio.dto.constraints.NotEmptyNotBlank;
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

    @NotEmptyNotBlank(message = "Not Empty and Blank Should Be Enter Min 3 Character")
    private String fooditemName;

    @NotEmpty(message = "Please Enter The Price")
    private double price;

    @NotEmptyNotBlank(message = "Not Empty and Blank Should Be Enter Min 3 Character")
    private String description;

    @NotEmpty(message = "Please Enter The Stock")
    private long stock;

    private Availability availability;
    private DietType dietType;
    private String cuisineType;
    private List<String> categories;

}
