package com.example.dio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class CuisineType {

    @Id
    private String cuisines;

    @ManyToMany(mappedBy = "cuisineTypes",fetch = FetchType.EAGER)
    private List<Restaurant> restaurants;
}
