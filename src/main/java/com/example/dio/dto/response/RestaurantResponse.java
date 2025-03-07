package com.example.dio.dto.response;

import com.example.dio.enums.DietType;
import com.example.dio.model.CuisineType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RestaurantResponse {

    private String name;
    private String address;
    private LocalTime opensAt;
    private LocalTime closeAt;
    private List<DietType> diettypes;
    private List<String> cuisineTypes;

}
