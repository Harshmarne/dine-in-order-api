package com.example.dio.dto.response;

import com.example.dio.enums.UserRole;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class UserResponse {
    private long userid;
    private String username;
    private UserRole userRole;
    private LocalDate createAt;
    private LocalDate lastModifiedAt;


}
