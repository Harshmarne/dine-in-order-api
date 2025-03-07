package com.example.dio.dto.request;

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

    @NotEmpty(message = "Username cannot be Empty")
    @NotBlank(message = "Username cannot be blank")
    private String name;

    @NotEmpty(message = "Username cannot be Empty")
    @NotBlank(message = "Username cannot be blank")
    private String address;

    @NotEmpty(message = "Username cannot be Empty")
    @NotBlank(message = "Username cannot be blank")
    private String contactnumber;

    @NotEmpty (message = "Email cannot be Empty")
    @NotBlank (message = "Username cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$",message = "Email is invalid")
    private String email;

    private LocalTime opensAt;
    private LocalTime closeAt;

    private List<DietType> diettypes;
    private List<String> cuisineTypes;

}
