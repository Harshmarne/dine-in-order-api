package com.example.dio.dto.request;

import com.example.dio.dto.constraints.Email;
import com.example.dio.dto.constraints.Password;
import com.example.dio.dto.constraints.PhoneNumber;
import com.example.dio.dto.constraints.Username;
import com.example.dio.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistertionRequest {

    @Username
    private String username;

    @Email
    private String email;

    @Password
    private String password;

    @PhoneNumber
    private String phoneNumber;

    private UserRole userRole;
}
