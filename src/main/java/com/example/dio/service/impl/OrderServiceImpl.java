package com.example.dio.service.impl;

import com.example.dio.dto.response.OrderResponse;
import com.example.dio.enums.BillStatus;
import com.example.dio.enums.OrderStatus;
import com.example.dio.enums.TableStatus;
import com.example.dio.exception.CartItemsNotFoundException;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final RestaurantTableRepositry tableRepositry;
    private final OrderRepositry orderRepositry;
    private final CartItemRepositry cartItemRepositry;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse createdOrder(long resturantTableId) {
        // Fetch the table and cartItems in one step
        List<CartItem> cartItems = cartItemRepositry.findByIsOrderedAndRestaurantTable_tableid(OrderStatus.NOT_ORDERED,resturantTableId);

        if (cartItems.isEmpty()) {
            throw new CartItemsNotFoundException("No cart items found to order for table: " + resturantTableId);
        }

        RestaurantTable restaurantTable = cartItems.get(0).getRestaurantTable();  // Assuming all cart items belong to the same table

        RestaurantOrder order = new RestaurantOrder();
        order.setOrderStatus(BillStatus.NOT_BUILD);
        order.setCartItems(cartItems);
        order.setRestaurantTable(restaurantTable);
        order.setTotalAmount(cartItems.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum());

        orderRepositry.save(order);

        // Update the table status to available after the order is placed
        restaurantTable.setStatus(TableStatus.AVAILABLE);
        tableRepositry.save(restaurantTable);

        // Update each cart item to reflect that it's ordered
        cartItems.forEach(item -> item.setIsOrdered(OrderStatus.ORDERED));
        cartItemRepositry.saveAll(cartItems);

        // Return a response map
        return orderMapper.mapToOrderResponse(order);
    }

}
