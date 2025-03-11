package com.example.dio.model;

import com.example.dio.enums.Availability;
import com.example.dio.enums.DietType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(indexes = {@Index(name = "idx_name",columnList = "fooditem_name")
})
@EntityListeners(AuditingEntityListener.class)
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fooditemId;
    private String fooditemName;
    private double price;
    private String description;
    private long stock;

    @Enumerated(EnumType.STRING)
    private Availability availability;

    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate lastModifiedAt;

    @Enumerated(EnumType.STRING)
    private DietType dietType;

    @ManyToOne
    private CuisineType cuisineType;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToMany
    private List<Category> categories;


}
