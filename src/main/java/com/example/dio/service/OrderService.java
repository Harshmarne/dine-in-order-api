package com.example.dio.service;

import com.example.dio.dto.response.OrderResponse;
import com.example.dio.enums.OrderStatus;

public interface OrderService {

    OrderResponse createdOrder(long resturantId);
}
