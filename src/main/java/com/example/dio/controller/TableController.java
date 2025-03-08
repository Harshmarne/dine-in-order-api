package com.example.dio.controller;

import com.example.dio.dto.request.RestaurantTableRequest;
import com.example.dio.dto.response.RestaurantTableResponse;
import com.example.dio.service.impl.TableServiceImpl;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.ResponseStructure;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class TableController {

    private final TableServiceImpl tableService;

    @PostMapping("/table/{restaurantId}")
    public ResponseEntity<ResponseStructure<RestaurantTableResponse>> restaurantRegister(@Valid @RequestBody RestaurantTableRequest restaurantTableRequest, @PathVariable long restaurantId){
        RestaurantTableResponse restaurantTableResponse = tableService.addTable(restaurantTableRequest,restaurantId);
        return ResponseBuilder.created(restaurantTableResponse, "Table Created Successfully");
    }
}
