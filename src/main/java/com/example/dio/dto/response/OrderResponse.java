package com.example.dio.dto.response;

import com.example.dio.enums.BillStatus;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import java.time.LocalDate;


@Getter
@Setter
public class OrderResponse {

    private long orderId;

    private BillStatus orderStatus;

    private LocalDate orderAt;

    private List<CartItemResponse> cartItems;

    private double totalAmount;
}
