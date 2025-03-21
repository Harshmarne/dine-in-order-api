package com.example.dio.dto.request;

import com.example.dio.dto.constraints.Email;
import com.example.dio.dto.constraints.NotEmptyNotBlank;
import com.example.dio.dto.constraints.PhoneNumber;
import com.example.dio.enums.DietType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RestaurantRequest {

    @NotEmptyNotBlank(message = "Not Empty and Blank Should Be Enter Min 3 Character")
    private String name;

    @NotEmptyNotBlank(message = "Not Empty and Blank Should Be Enter Min 3 Character")
    private String address;

    @PhoneNumber
    private String contactnumber;

    @Email
    private String email;

    private LocalTime opensAt;
    private LocalTime closeAt;

    private List<DietType> diettypes;
    private List<String> cuisineTypes;

}
