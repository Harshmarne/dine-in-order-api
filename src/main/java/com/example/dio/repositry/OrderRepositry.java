package com.example.dio.repositry;

import com.example.dio.enums.BillStatus;
import com.example.dio.model.RestaurantOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepositry extends JpaRepository<RestaurantOrder,Long> {

    List<RestaurantOrder> findByorderStatusAndRestaurantTable_tableid(BillStatus billStatus, Long restaurantTableId);

    RestaurantOrder findRestaurantTableByOrderId(long id);
}
