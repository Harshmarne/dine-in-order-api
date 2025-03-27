package com.example.dio.model;


import com.example.dio.enums.DietType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedBy
    private String CreatedBy;

    @Enumerated(EnumType.STRING)
    private List<DietType> diettypes;

    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate lastModifiedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<CuisineType> cuisineTypes;

    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin;

    @OneToMany(mappedBy = "restaurants")
    private List<RestaurantTable> restaurantTables;

    @OneToMany(mappedBy = "restaurant")
    private List<FoodItem> foodItems;
}
