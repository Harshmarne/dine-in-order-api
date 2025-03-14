package com.example.dio.model;

import com.example.dio.enums.BillStatus;
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
public class RestaurantOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    private BillStatus orderStatus;

    @CreatedDate
    private LocalDateTime orderAt;

    private double totalAmount;

    @OneToMany
    private List<CartItem> cartItems;

    @ManyToOne
    private RestaurantTable restaurantTable;
}
