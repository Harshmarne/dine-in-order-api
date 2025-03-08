package com.example.dio.dto.response;

import com.example.dio.enums.TableStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantTableResponse {
    private long tableno;
    private long tableCapacity;
    private TableStatus status;
}
