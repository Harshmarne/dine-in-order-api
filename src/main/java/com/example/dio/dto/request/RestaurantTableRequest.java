package com.example.dio.dto.request;

import com.example.dio.enums.TableStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class RestaurantTableRequest {


    private long tableno;

    private long tableCapacity;

    private TableStatus status;
}
