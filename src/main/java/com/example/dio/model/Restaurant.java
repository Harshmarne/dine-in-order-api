package com.example.dio.model;


import com.example.dio.enums.DietType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
    private long restaurantid;
    private String name;
    private String address;
    private String contactnumber;
    private String email;
    private LocalTime opensAt;
    private LocalTime closeAt;
    private DietType diettype;
    private LocalDate createdAt;
    private LocalDate lastModifiedAt;

    @ManyToMany(mappedBy = "restaurants",fetch = FetchType.EAGER)
    private List<CuisineType> cuisineType;

}
