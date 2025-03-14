package com.example.dio.service.impl;

import com.example.dio.dto.response.OrderResponse;
import com.example.dio.enums.BillStatus;
import com.example.dio.enums.OrderStatus;
import com.example.dio.enums.TableStatus;
import com.example.dio.exception.RestaurantTableNotFoundByException;
import com.example.dio.mapper.OrderMapper;
import com.example.dio.model.CartItem;
import com.example.dio.model.RestaurantOrder;
import com.example.dio.model.RestaurantTable;
import com.example.dio.repositry.CartItemRepositry;
import com.example.dio.repositry.OrderRepositry;
import com.example.dio.repositry.RestaurantTableRepositry;
import com.example.dio.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final RestaurantTableRepositry tableRepositry;
    private final OrderRepositry orderRepositry;
    private final CartItemRepositry cartItemRepositry;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse createdOrder(long resturantTableId) {
        RestaurantTable restaurantTable = tableRepositry.findById(resturantTableId)
                .orElseThrow(() -> new RestaurantTableNotFoundByException("Table Not Found !!"));

        List<CartItem> cartItems = cartItemRepositry.findByIsOrderedAndRestaurantTable_tableid(OrderStatus.NOT_ORDERED,resturantTableId);


        RestaurantOrder order = null ;

        if(!cartItems.isEmpty()){
            order = new RestaurantOrder();
            order.setOrderStatus(BillStatus.NOT_BUILD);
            order.setCartItems(cartItems);
            order.setRestaurantTable(restaurantTable);
            order.setTotalAmount(cartItems.stream()
                    .mapToDouble(CartItem::getTotalPrice)
                    .sum());
            orderRepositry.save(order);
        }
        else{
            throw new NoSuchElementException(" No CartItem Selected !! ");
        }

        restaurantTable.setStatus(TableStatus.AVAILABLE);
        tableRepositry.save(restaurantTable);

        cartItems.forEach(item -> item.setIsOrdered(OrderStatus.ORDERED));
        cartItemRepositry.saveAll(cartItems);

        return orderMapper.mapToOrderResponse(order);
    }

}
