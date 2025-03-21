package com.example.dio.dto.request;

import com.example.dio.dto.constraints.Email;
import com.example.dio.dto.constraints.PhoneNumber;
import com.example.dio.dto.constraints.Username;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @Username
    private String username;

    @Email
    private String email;

    @PhoneNumber
    private String phoneNumber;
}
