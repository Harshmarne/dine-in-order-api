package com.example.dio.controller;

import com.example.dio.dto.response.OrderResponse;
import com.example.dio.service.OrderService;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class RestaurantOrderController {

    private final OrderService orderService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/orders/tables/{resturantTableId}")
    public ResponseEntity<ResponseStructure<OrderResponse>> placeOrder(@PathVariable long resturantTableId){
        OrderResponse orderResponse = orderService.createdOrder(resturantTableId);
        return ResponseBuilder.created(orderResponse,"Your Order Is Placed");
    }
}
