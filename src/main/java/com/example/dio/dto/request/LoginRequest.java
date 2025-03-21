package com.example.dio.dto.request;

import com.example.dio.dto.constraints.Email;
import com.example.dio.dto.constraints.Password;

public record LoginRequest(
       @Email String email,
       @Password String password
){}
