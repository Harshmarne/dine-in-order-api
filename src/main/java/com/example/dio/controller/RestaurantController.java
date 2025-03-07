package com.example.dio.controller;


import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.service.RestaurantService;
import com.example.dio.service.impl.UserServiceImpl;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.ResponseStructure;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/restaurant/{userId}")
    public ResponseEntity<ResponseStructure<RestaurantResponse>> restaurantRegister(@Valid @RequestBody RestaurantRequest restaurantRequest, @PathVariable long userId){
        System.out.println(restaurantRequest.getCuisineTypes());
        RestaurantResponse restaurantResponse = restaurantService.restaurant(restaurantRequest,userId);
        return ResponseBuilder.created(restaurantResponse , "Restaurant Created Successfully");
    }
}
