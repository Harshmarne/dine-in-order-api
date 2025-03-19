package com.example.dio.service.impl;

import com.example.dio.dto.response.BillResponse;
import com.example.dio.enums.BillStatus;
import com.example.dio.enums.TableStatus;
import com.example.dio.exception.NoBillFoundException;
import com.example.dio.mapper.BillMapper;
import com.example.dio.model.Bill;
import com.example.dio.model.Restaurant;
import com.example.dio.model.RestaurantOrder;
import com.example.dio.model.RestaurantTable;
import com.example.dio.repositry.BillRespositry;
import com.example.dio.repositry.OrderRepositry;
import com.example.dio.repositry.RestaurantRepositry;
import com.example.dio.repositry.RestaurantTableRepositry;
import com.example.dio.service.BillService;
import com.example.dio.utility.BillGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class BillServiceImpl implements BillService {

     private final OrderRepositry orderRepositry;
     private final RestaurantTableRepositry tableRepositry;
     private final BillRespositry billRespositry;
     private final BillMapper billMapper;
     private final RestaurantRepositry restaurantRepositry;
     private final BillGenerator billGenerate;

    @Override
    public BillResponse billGenerate(long tableId) {

        RestaurantTable restaurantTable = tableRepositry.findById(tableId)
                .orElseThrow(() -> new NoSuchElementException("Table not found !!"));

        List<RestaurantOrder> orderList = orderRepositry.findByorderStatusAndRestaurantTable_tableid(BillStatus.NOT_BUILD,tableId);
        double totalAmount = orderList.stream()
                .mapToDouble(RestaurantOrder::getTotalAmount)
                .sum();

        Bill bill = null;
        if(!orderList.isEmpty()) {
            bill = new Bill();
            bill.setRestaurantOrders(orderList);
            bill.setTotalPayableAmount(totalAmount);
            billRespositry.save(bill);
        }
        else{
            throw new NoSuchElementException(" No CartItem Selected !! ");
        }

        orderList.forEach(order -> order.setOrderStatus(BillStatus.BUILD));
        restaurantTable.setStatus(TableStatus.AVAILABLE);
        tableRepositry.save(restaurantTable);
        orderRepositry.saveAll(orderList);

        return billMapper.mapToBillResponse(bill);
    }

    /**
     * @param billId
     * @return
     */
    @Override
    public BillResponse findById(long billId) {
        Bill bill = billRespositry.findById(billId)
                .orElseThrow(() -> new NoBillFoundException("No bill found with "+ billId +" id"));
        return billMapper.mapToBillResponse(bill);

    }

    /**
     * @param billId
     * @return
     */
    @Override
    public byte[] findBillById(long billId) throws IOException {
       BillResponse billResponse = this.findById(billId);

       long foodId = billResponse.getRestaurantOrders().getFirst().getCartItems().getFirst().getFoodItem().getFooditemId();

        Restaurant restaurantName = restaurantRepositry.findNameByFoodItems_fooditemId(foodId);

        long orderId = billResponse.getRestaurantOrders().getFirst().getOrderId();

        RestaurantOrder table = orderRepositry.findRestaurantTableByOrderId(orderId);

        Map<String , Object > data = Map.of("restaurantName",restaurantName.getName(),"tableNo",table.getRestaurantTable().getTableno(),"bill",billResponse);

        return billGenerate.generateTopdf("Bill",data);

    }
}
