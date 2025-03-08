package com.example.dio.model;


import com.example.dio.enums.DietType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long restaurantid;

    private String name;
    private String address;
    private String contactnumber;
    private String email;
    private LocalTime opensAt;
    private LocalTime closeAt;

    @Enumerated(EnumType.STRING)
    private List<DietType> diettypes;
    private LocalDate createdAt;
    private LocalDate lastModifiedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<CuisineType> cuisineTypes;

    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin;

    @OneToMany(mappedBy = "restaurants")
    private List<RestaurantTable> restaurantTables;
}
