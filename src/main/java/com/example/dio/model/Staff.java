package com.example.dio.model;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;


@Entity
@Table(name = "staffs")
public class Staff extends User{

    @ManyToMany
    private List<RestaurantTable> restaurantTables;
}
