package com.example.dio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long billId;

    @CreatedDate
    private LocalDateTime generatedAt;

    private double totalPayableAmount;

    @OneToMany
    private List<RestaurantOrder> restaurantOrders;
}
