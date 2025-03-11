package com.example.dio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class CuisineType {

    @Id
    private String cuisines;

    @OneToMany(mappedBy = "cuisineType")
    private List<FoodItem> foodItems;

    @ManyToMany(mappedBy = "cuisineTypes",fetch = FetchType.EAGER)
    private List<Restaurant> restaurants;
}
