package com.example.dio.model;


import com.example.dio.enums.TableStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tableid;

    private long tableno;
    private long tableCapacity;

    @Enumerated(EnumType.STRING)
    private TableStatus status;

    @ManyToMany(mappedBy = "restaurantTables")
    private List<Staff> staffs;

    @ManyToOne
    private Restaurant restaurants;
}
