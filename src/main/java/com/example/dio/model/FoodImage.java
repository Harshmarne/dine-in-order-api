package com.example.dio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FoodImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imageId;
    private String imageURL;

    @ManyToOne
    private FoodItem foodItem;
}
