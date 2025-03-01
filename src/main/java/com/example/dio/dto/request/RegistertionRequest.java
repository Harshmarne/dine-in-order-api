package com.example.dio.dto.request;

import com.example.dio.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistertionRequest {

    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private UserRole userRole;
}
